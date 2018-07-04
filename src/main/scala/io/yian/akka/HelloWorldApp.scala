package io.yian.akka

import akka.actor.{ActorSystem, Actor};
import akka.actor.Props
import akka.event.Logging

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
class HelloWorldActor(name:String) extends Actor {
  // Actor initialize
  override def preStart = { println(name + " is started.")}

  // message received
  def receive = {
    case msg:String => {
      println("HelloWorldActor:Hello World!" + msg + " My name is "+ name)
      "HelloWorldActor: Hello world!" + msg + " My name is " + name
    }
  }

  override def postStop = { println(name + " is stopped.") }
}

object HelloWorldApp extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("HelloWorldApp")
    val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActor("actor1")), "HelloWorldActor")

    val result1 = helloWorldActor ! """Test1"""
    val result2 = helloWorldActor ! """Test2"""

    println("Test1 result is " + result1)
    println("Test2 result is " + result2)

    Thread.sleep(500)
    system.shutdown()
  }
}
