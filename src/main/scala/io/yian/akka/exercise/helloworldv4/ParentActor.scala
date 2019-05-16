package io.yian.akka.exercise.helloworldv4

import akka.actor.{Actor, ActorRef}

class ParentActor(name: String, child: ActorRef) extends Actor {
  /** 메시지 수신 처리 */
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
