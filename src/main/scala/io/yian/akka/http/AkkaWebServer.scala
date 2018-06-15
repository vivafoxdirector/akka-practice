package io.yian.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

// ref: https://qiita.com/yabaiwebyasan/items/a4a8be77f39d4a357093
// ref: https://doc.akka.io/docs/akka-http/current/introduction.html#using-akka-http
// ref: https://qiita.com/ovrmrw/items/e00e3cadde318d599bbd
// ref: https://qiita.com/xoyo24/items/299ee3e624f4afe2d27a
// ref: http://takkkun.hatenablog.com/entry/2017/03/21/Akka_HTTP%E3%82%92%E4%BD%BF%E3%81%A3%E3%81%A6%E3%81%BF%E3%82%8B%E3%80%82Akka_Streams%E3%81%AB%E3%82%82%E3%81%A1%E3%82%87%E3%81%A3%E3%81%A8%E8%A7%A6%E3%82%8C%E3%82%8B%E3%82%88
// ref: http://blog.colinbreck.com/akka-streams-a-motivating-example/
// ref: https://www.programcreek.com/scala/akka.http.scaladsl.server.Route
// ref: https://doc.akka.io/docs/akka-http/current/introduction.html#using-akka-http
// ref: https://doc.akka.io/docs/akka-http/current/routing-dsl/overview.html
// ref: https://engineering.linecorp.com/ja/blog/detail/229

object AkkaWebServer {
    def main(args: Array[String]): Unit = {
        implicit val system:ActorSystem = ActorSystem("my-system")
        implicit val meterializer:ActorMaterializer = ActorMaterializer()
        // needed for the future flatMap/onComplete in the end
        implicit val executionContext:ExecutionContextExecutor  = system.dispatcher

        val route =
            path("hello") {
                get {
                    val html = s"<h1>Say Hello to akka-http</h1>"
                    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, html))
                }
            }

        val bindingFuture = Http().bindAndHandle(route, "localhost", 18090)
        println(s"Server online at http://localhost:18090/hello\nPresss Return to stop...")
        StdIn.readLine() // let it run until user presses return
        bindingFuture
                .flatMap(_.unbind())    // trigger unbinding from the port
                .onComplete(_ => system.terminate())    // and shutdown when done
    }
}
