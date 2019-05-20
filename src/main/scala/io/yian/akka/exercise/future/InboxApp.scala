package io.yian.akka.exercise.future

import akka.actor.{ActorDSL, ActorSystem, Props}
import io.yian.akka.exercise.configfile.MessagePrintActor

object InboxApp extends App {
  override def main(args: Array[String]) : Unit = {
    implicit val system = ActorSystem.apply("ConfiguredRoutingApp")

    val actor1 = system.actorOf(Props[MessagePrintActor])

    // Inbox#send 메소드를 사용으로 Actor로부터의 수신메시지가 누적된다.
    // => Watch메소드를 사용하였지만 제대로 안됨.
    val rootInbox = ActorDSL.inbox()
    rootInbox.send(actor1, "Test1")
    rootInbox.send(actor1, "Test2")
    rootInbox.send(actor1, "Test3")
    rootInbox.send(actor1, "Test4")

    Thread.sleep(1000)

    // 누적후에 receive메소드를 사용
    val msg1 = rootInbox.receive()
    println("Receive1:" + msg1)
    val msg2 = rootInbox.receive()
    println("Receive2:" + msg2)
    val msg3 = rootInbox.receive()
    println("Receive3:" + msg3)

    Thread.sleep(5000)
    system.shutdown()
  }
}
