package io.yian.akka.exercise.helloworldv3

import akka.actor.{ActorSystem, Props}

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
object HelloWorldApp extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("HelloWorldApp")
    val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActor("actor1")), "HelloWorldActor")

    val result1 = helloWorldActor ! """Test1"""
    val result2 = helloWorldActor ! """Test2"""

    // 송신측에서 보낸 메시지가 수신 받을거라 예상했지만 수신 받는 못했다. (비동기 때문인가??!!!)
    // 이것을 개선한 것이 helloWorv4이다.
    println("Test1 result is " + result1)
    println("Test2 result is " + result2)

    Thread.sleep(5000)
    system.shutdown()
  }
}
