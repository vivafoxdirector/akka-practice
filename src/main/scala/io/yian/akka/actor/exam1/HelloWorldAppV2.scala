package io.yian.akka.actor.exam1

import akka.actor.{ActorSystem, Actor};
import akka.actor.Props
import akka.event.Logging

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
class HelloWorldActorV2(name:String) extends Actor {
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
      sender ! "HelloWorldActor: Hello world!" + msg + " My name is " + name
    }
  }

  /**
   *  Actor 종료 처리
   */
  override def postStop = { println(name + " is stopped.") }
}

object HelloWorldAppV2 extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("HelloWorldAppV2")
    val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActorV2("actor1")), "HelloWorldActor")

    val result1 = helloWorldActor ! """Test1"""
    val result2 = helloWorldActor ! """Test2"""

    println("Test1 result is " + result1)
    println("Test2 result is " + result2)

    Thread.sleep(500)
    system.shutdown()
  }
}
