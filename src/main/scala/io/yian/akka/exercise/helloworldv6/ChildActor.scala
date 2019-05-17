package io.yian.akka.exercise.helloworldv6

import akka.actor.Actor

class ChildActor(name: String) extends Actor {
  /** 메시지 수신 처리 */
  def receive = {
    case msg : String => {
      val message = "ChildActor: Received String " + msg + " My name is " + name
      println("msg: " + message)
      sender ! message.length
    }
  }
}
