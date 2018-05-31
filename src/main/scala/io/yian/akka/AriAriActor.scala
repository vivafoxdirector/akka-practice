package io.yian.akka

import akka.actor.{ActorSystem, Actor};
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

object Main {
  def main(args: Array[String]):Unit = {
    // 무거운 처리. 어플리케이션 내에 하나만 만들도록 한다.
    val system = ActorSystem("mySystem")
  }
}
