package io.yian.akka.exercise.helloworldv5

import akka.actor.Actor

/**
  * helloworldv3에서 Actor로부터 응답 메시지를 수신할 수있는지 테스트 해보았으나, 공백문자를 수신 받았다.
  * 이를 수신할 수 있는 방법을 알아본다.
  * akka.patterns.ask를 사용하여 처리 하게 되면 Future형의 받환값을
  * 받을 수 있다.
  *
  * 단, 직접 akka.patterns.ask를 확인하면 암묵적으로 timeout이 필요하기 때문에 사용하기 전에
  * 암묵적으로 정의해둘 필요가 있다. (AskSupport::AskableActorRef를 확인)
  *
  * ref: http://kimutansk.hatenablog.com/entry/20140726/1406330944
  */
class HelloWorldActor(name: String) extends Actor {
    /** Actor초기화 처리 */
    override def preStart = {
        println(name + " is started.")
    }
    
    /** 메시지 수신 처리 */
    override def receive: Receive = {
        case msg : String => {
            println("HelloWorldActor: Hello world! " + msg + " My name is " + name)
            sender ! "HelloWorldActor: Hello world! " + msg + " My name is " + name
        }
    }
    
    /** Actor 종료 처리 */
    override def postStop = {
        println(name + " is stopped.")
    }
}