package io.yian.akka.exercise.helloworldv2

import akka.actor.Actor

class HelloWorldActor(name:String) extends Actor {
  override def preStart = {
    println(name + " is started.")
  }

  override def receive: Receive = {
    case msg : String => {
      println("HelloWorldActor: Hello world! "+ msg + " My name is " + name)
    }
  }

  override def postStop = {
    println(name + " is stopped.")
  }
}
