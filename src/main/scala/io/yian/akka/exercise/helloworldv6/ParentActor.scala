package io.yian.akka.exercise.helloworldv6

import akka.actor.{Actor, ActorRef}
import akka.routing.{ActorRefRoutee, Router, RoutingLogic}

import scala.collection.immutable

/**
 *
 */
class ParentActor(name: String, childActorList: immutable.IndexedSeq[ActorRef], routingLogic: RoutingLogic) extends Actor {

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