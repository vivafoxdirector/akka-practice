package io.yian.akka.exercise.helloworldv1

import akka.actor.Actor

/**
  * Akka Actor용 HelloWorld클래스
  * 수신받은 메시지에 첨언하여 출력한다.
  * ref: http://kimutansk.hatenablog.com/entry/20140721/1405949716
  */
class HelloWorldActor extends Actor {
    override def receive: Receive = {
        case x => println("Hello World!" + x)
    }
}
