package edu.knoldus.actors

import akka.actor.{Actor, ActorLogging}
import akka.dispatch.{BoundedMessageQueueSemantics, RequiresMessageQueue}
import edu.knoldus.models.Account
import edu.knoldus.services.UserAccountService

class AccountGeneratorActor(accountService: UserAccountService) extends Actor with ActorLogging
  with RequiresMessageQueue[BoundedMessageQueueSemantics] {

  override def receive = {
    case account: Account =>
      self ! checkUser(account.username, account)
      //val bill = context.actorOf(Props[BillProcessorActor].withDispatcher("my-dispatcher"), "billProcessor")
    //case _ => log.error("Not a valid choice")
  }

  def checkUser(username: String, account: Account) = {
    if(accountService.accountInfo.username.contains(username)) {
      log.error("Username already exists")
    } else {
      sender() ! accountService.addUser(account)
      log.info("Adding account information")
    }
  }

}
