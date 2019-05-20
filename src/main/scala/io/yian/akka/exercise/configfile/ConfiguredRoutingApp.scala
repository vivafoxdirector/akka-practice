package io.yian.akka.exercise.configfile

import akka.actor.{ActorSystem, Props}
import akka.routing.FromConfig

/**
  * application.conf 설정파일에서 Actor(Router) 정의를 읽어들인다.
  * Router의 MessagePrintActor가 3개 생성되고, RoundRobin방식으로
  * 송신되는 것을 확인 한다.
  * router = round-robin-pool 설정하면 왜 RoundRobinPool이 호출되는지 확인하려면
  * reference.conf를 확인하면 round-robin-pool은 RoundRobinPool 클래스로 지정되어 있다.
  * 이로써 자신이 만든 클래스를 읽어들이도록 하는 경우 클래스명을 지정하면 된다.
  * ref: http://kimutansk.hatenablog.com/entry/20140809/1407572746
  */
object ConfiguredRoutingApp extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("ConfiguredRoutingApp")
    val router1 = system.actorOf(FromConfig.props(Props[MessagePrintActor]), "router1")

    router1 ! "Test1"
    router1 ! "Test2"
    router1 ! "Test3"
    router1 ! "Test4"

    Thread.sleep(5000)
    system.shutdown()
  }

}
