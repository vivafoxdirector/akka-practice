package io.yian.akka.exercise.helloworldv1

import akka.actor.{ActorSystem, Props}

/**
  * Akka Actor용 Hello World기동 클래스
  * ref: http://kimutansk.hatenablog.com/entry/20140721/1405949716
  */
object HelloWorldApp {
    def main(args:Array[String]) : Unit = {
        // ① AkkaActorSystem생성
        val system = ActorSystem.apply("HelloWorldApp")
        // ② 설정으로 HelloWorldActor 생성
        val helloWorldActor = system.actorOf(Props.apply[HelloWorldActor], "HelloWorldActor")

        // ③ Actor 송수신 처리
        // HelloWorldActor에게 "Test1" 메시지 송신
        helloWorldActor ! """Test1"""

        // HelloWorldActor에게 "Test2" 메시지 송신
        helloWorldActor ! """Test2"""

        // ④ AkkaActorSystem 종료
        system.shutdown()
    }
}
