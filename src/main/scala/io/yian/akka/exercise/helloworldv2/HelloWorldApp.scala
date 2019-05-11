package io.yian.akka.exercise.helloworldv2

import akka.actor.{ActorSystem, Props}

// ref: http://kimutansk.hatenablog.com/entry/20140724/1406151221
object HelloWorldApp extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("HelloWorldApp")
    val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActor("actor1")), "HelloWorldActor")

    helloWorldActor ! """Test1"""
    helloWorldActor ! """Test2"""
    helloWorldActor ! 12345

    system.shutdown()
  }
}
