package io.yian.akka

import akka.actor.Actor;
import akka.actor.Props
import akka.event.Logging

// https://doc.akka.io/docs/akka/current/actors.html

class AriAriActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "AriAri" => println("World")
    case _ => log.info("received unknown message")
  }
}
