package edu.knoldus.actors

import akka.actor.{ActorLogging, Actor}
import edu.knoldus.models.Account

import scala.concurrent.Future

class ReportGeneratorActor(billRef: Future[Any]) extends Actor with ActorLogging {

  override def receive = {
    case account: Account =>
      log.info("--------------------------------------------------------------------------------")
      log.info("Bills of " + account.accHolder)
      log.info("List is: " + billRef)
      log.info("--------------------------------------------------------------------------------")
  }

}
