package edu.knoldus.BillCategoryActors

import akka.actor.{ActorLogging, Actor}
import edu.knoldus.models.{Account, Biller}
import edu.knoldus.services.BillerService

class InternetActor(billerService: BillerService, biller: Biller) extends Actor with ActorLogging {

  override def receive = {
    case account: Account =>
      self ! getCarBill(account)
  }

  def getCarBill(account: Account) = {
    sender() ! billerService.payAmt(account, biller)
    log.info("internet bill has been paid")
  }

}
