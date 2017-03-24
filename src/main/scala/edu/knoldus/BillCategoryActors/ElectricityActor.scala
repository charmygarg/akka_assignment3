package edu.knoldus.BillCategoryActors

import akka.actor.{ActorLogging, Actor}
import edu.knoldus.models.Biller
import edu.knoldus.services.BillerService

class ElectricityActor(billerService: BillerService) extends Actor with ActorLogging {

  override def receive = {
    case biller: Biller =>
      self ! getCarBill(biller.category, biller, biller.accNo, biller.amtPaid)
  }

  def getCarBill(category: String, biller: Biller, accNo: Long, amtPaid: Float) = {
    if(category.toLowerCase == "electricity") {
      sender() ! billerService.payAmt(amtPaid, biller)
      log.info("electricity bill has been paid")
    }
  }

}
