package io.yian.akka.exercise.configfile

import akka.actor.Actor

class MessagePrintActor extends Actor {
  def receive: Receive = {
    case msg : String => {
      val message = self.path + ": Received string" + msg
      println(message)
    }
  }
}
