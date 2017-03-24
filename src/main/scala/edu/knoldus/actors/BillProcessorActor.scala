package edu.knoldus.actors

import akka.actor.{Props, ActorLogging, Actor}
import edu.knoldus.BillCategoryActors._
import edu.knoldus.models.{Account, Biller}
import edu.knoldus.services.BillerService

class BillProcessorActor(billerService: BillerService, account: Account, biller: Biller) extends Actor with ActorLogging {

  override def receive = {
    case "car" =>
      val carRef = context.actorOf(Props(classOf[CarActor], billerService, biller))
      carRef.forward(account)
    case "electricity" =>
      val electricityRef = context.actorOf(Props(classOf[ElectricityActor], billerService, biller))
      electricityRef.forward(account)
    case "food" =>
      val foodRef = context.actorOf(Props(classOf[FoodActor], billerService, biller))
      foodRef.forward(account)
    case "internet" =>
      val internetRef = context.actorOf(Props(classOf[InternetActor], billerService, biller))
      internetRef.forward(account)
    case "phone" =>
      val phoneRef = context.actorOf(Props(classOf[PhoneActor], billerService, biller))
      phoneRef.forward(account)
  }

}

