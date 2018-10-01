package io.yian.akka.httpslick.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import io.yian.akka.httpslick.http.DogRoutes
import io.yian.akka.httpslick.slick.dao.DogDao
import io.yian.akka.httpslick.slick.model.Dog

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

// 참조: https://qiita.com/yabaiwebyasan/items/a4a8be77f39d4a357093
object Server extends App with DogRoutes with DogDao {
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer:ActorMaterializer = ActorMaterializer()
  implicit val dispatcher: ExecutionContextExecutor = system.dispatcher

  ddl.onComplete {
    _=>
      create(Dog("Bailey"))
      create(Dog("Max"))
      create(Dog("Charlie"))
      create(Dog("Bella"))
      create(Dog("Luch"))
      create(Dog("Molly"))
      val bindingFuture = Http().bindAndHandle(routes, "localhost", 18080)
      println(s"Server online at http://locahost:18080/\nPress Return to stop...")
      StdIn.readLine()
      bindingFuture
        .flatMap(_.unbind())
        .onComplete(_=> system.terminate())
  }
}
