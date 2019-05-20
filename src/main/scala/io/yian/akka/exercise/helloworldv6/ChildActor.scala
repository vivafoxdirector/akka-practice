package io.yian.akka.exercise.helloworldv6

import akka.actor.Actor

/**
  * Router는 Routing을 하는 Logic클래스와 Routing되는 RouteeList가 필요하다
  * ref: http://kimutansk.hatenablog.com/entry/20140803/1407075420
  * @param name
  */
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
