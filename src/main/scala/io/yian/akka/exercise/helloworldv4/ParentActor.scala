package io.yian.akka.exercise.helloworldv3

import akka.actor.{Actor, ActorRef}

class ParentActor(name: String, child: ActorRef) extends Actor {
  def receive = {
    case msg : String => {
      println("ParentActor: Received String " + msg + " My name is  " + name)
      child ! "Hello World!" + msg + " My name is " + name
    }

    case msg : Int => {
      println("ParentActor: Received Int " + msg + " My name is " + name)
    }
  }
}
