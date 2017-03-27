package edu.knoldus.actors

import akka.actor.{ActorRef, ActorSystem, Actor}
import edu.knoldus.models.Account
import scala.concurrent.duration._

class Scheduler(dbRef: ActorRef, system: ActorSystem) extends Actor{

  override def receive = {
    case account: Account =>
      import system.dispatcher
      val cancellable =
        system.scheduler.schedule(
          10 second,
          10 second,
          dbRef,
          account)
  }
}
