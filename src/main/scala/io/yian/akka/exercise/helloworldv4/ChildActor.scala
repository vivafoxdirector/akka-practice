package io.yian.akka.exercise.helloworldv3

import akka.actor.Actor

class ChildActor(name: String) extends Actor {
  def receive = {
    case msg : String => {
      val message = "ChildActor: Received String " + msg + " My name is " + name
      println("msg: " + message)
      println("sender : " + sender)
      println("sender class : " + sender.getClass)
      sender ! message.length
    }
  }
}
