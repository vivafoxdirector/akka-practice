package io.yian.akka.exercise.helloworldv6

import akka.actor.{Actor, ActorRef}
import akka.routing.{ActorRefRoutee, Router, RoutingLogic}

import scala.collection.immutable

/**
  * Router는 Routing을 하는 Logic클래스와 Routing되는 RouteeList가 필요하다
  * ref : http://kimutansk.hatenablog.com/entry/20140803/1407075420
  * @param name
  * @param childActorList
  * @param routingLogic
  */
class ParentActor(name: String, childActorList: immutable.IndexedSeq[ActorRef], routingLogic: RoutingLogic) extends Actor {

  // 초기화활 때 부여되는 ActorList에 저장된 순으로 송신하도록 초기화
  val routes = immutable.IndexedSeq.tabulate(childActorList.size) (i => new ActorRefRoutee(childActorList(i)))
  val router = new Router(routingLogic, routes)

  /** 메시지 수신 처리 */
  def receive = {
    case msg : String => {
      println("ParentActor: Received String " + msg + " My name is  " + name)
      router.route(msg, self)
    }

    case msg : Int => {
      println("ParentActor: Received Int " + msg + " My name is " + name)
    }
  }
}