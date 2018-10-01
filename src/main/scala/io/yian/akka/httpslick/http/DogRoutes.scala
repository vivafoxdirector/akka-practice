package io.yian.akka.httpslick.http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.MethodDirectives
import io.yian.akka.httpslick.slick.dao.DogDao
import io.yian.akka.httpslick.slick.model.Dog

import scala.concurrent.ExecutionContextExecutor

// 참조: https://qiita.com/yabaiwebyasan/items/a4a8be77f39d4a357093
trait DogRoutes extends SprayJsonSupport {
  this:DogDao =>

  implicit val dispatcher : ExecutionContextExecutor

  // pathPrefix 은 URL부분의 아래와 같이 첫음 부분의 명칭에 해당한다.
  // http://localhost:8080/dogs
  val routes = pathPrefix("dogs") {
    pathEnd {
      get {
        complete(getAll)
      } ~ post {
        entity(as[Dog]) { dog =>
          complete {
            create(dog).map { result => HttpResponse(entity = "dog has been saved successfully")}
          }
        }
      }
    } ~ path(IntNumber) { id =>
      get {
        complete(getById(id))
      } ~ put {
        entity(as[Dog]) { dog =>
          complete {
            val newDog = Dog(dog.name, Option(id))
            update(newDog).map { result => HttpResponse(entity = "dog has been updated successfully") }
          }
        }
      } ~ MethodDirectives.delete {
        complete {
          delete(id).map {
            result => HttpResponse(entity = "dog has been deleted")
          }
        }
      }
    }
  }
}
