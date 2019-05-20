package io.yian.akka.exercise.helloworldv7.settingfilev2

import akka.actor.ActorSystem

/**
  * 설정파일 : resources/application.conf
  * -----------------------------------
  * akka {
  * loglevel = "DEBUG"
  * }
  * -----------------------------------
  * 먼저 akka 설정파일을 읽어들여 출력해 본다.
  *
  * 위 설정이 정상적으로 적용되었는지 아래의 예로 확인한다.
  * ref: http://kimutansk.hatenablog.com/entry/20140806/1407275104
  */
object ConfiguredRoutingApp extends App {
    override def main(args: Array[String]) : Unit = {
        val system = ActorSystem.apply("MessageSendApp")
        
        // 클래스 패스, 시스템 정보등 출력
        // application.conf 와 reference.conf를 혼합하여 출력
        // loglevel은 기본 INFO이나, application.conf의 설정으로 오버라이트 된다.
        // 그리고 주석내용까지 오버라이트 된다.
        println(system.settings)
    }
}
