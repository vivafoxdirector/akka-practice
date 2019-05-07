package io.yian.akka.exam1

import akka.actor.{ActorSystem, Actor};
import akka.actor.Props
import akka.event.Logging

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
class HelloWorldActorV1(name:String) extends Actor {
  /**
    * Actor 초기화 처리
    */
  override def preStart = { println(name + " is started.")}

  /**
    * 메시지 수신 처리
    * @return
    */
  def receive = {
    case msg:String => {
      println("HelloWorldActor:Hello World!" + msg + " My name is "+ name)
      "HelloWorldActor: Hello world!" + msg + " My name is " + name
    }
  }

  /**
   *  Actor 종료 처리
   */
  override def postStop = { println(name + " is stopped.") }
}

object HelloWorldAppV1 extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("HelloWorldAppV1")
    val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActorV1("actor1")), "HelloWorldActor")

    // 비동기 처리이기 때문에 반환값이 없다.
    val result1 = helloWorldActor ! """Test1"""
    val result2 = helloWorldActor ! """Test2"""

    println("Test1 result is " + result1)
    println("Test2 result is " + result2)

    Thread.sleep(500)
    system.shutdown()
  }
}
