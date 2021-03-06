package io.yian.akka.exercise.helloworldv4

import akka.actor.Actor

class ChildActor(name: String) extends Actor {
  /** 메시지 수신 처리 */
  def receive = {
    case msg : String => {
      val message = "ChildActor: Received String " + msg + " My name is " + name
      println("msg: " + message)
      println("sender : " + sender)
      
      // akka.actor.RepointableActorRef
      println("sender class : " + sender.getClass)
      sender ! message.length
    }
  }
}
