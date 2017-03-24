package edu.knoldus.actors

import akka.actor.{Props, ActorLogging, Actor}
import edu.knoldus.BillCategoryActors._
import edu.knoldus.models.Biller
import edu.knoldus.services.BillerService

class BillProcessorActor(billerService: BillerService, biller: Biller) extends Actor with ActorLogging {

  override def receive = {
    case "car" =>
      val carRef = context.actorOf(Props(classOf[CarActor], billerService))
      carRef.forward(biller)
    case "electricity" =>
      val electricityRef = context.actorOf(Props(classOf[ElectricityActor], billerService))
      electricityRef.forward(biller)
    case "food" =>
      val foodRef = context.actorOf(Props(classOf[FoodActor], billerService))
      foodRef.forward(biller)
    case "internet" =>
      val internetRef = context.actorOf(Props(classOf[InternetActor], billerService))
      internetRef.forward(biller)
    case "phone" =>
      val phoneRef = context.actorOf(Props(classOf[PhoneActor], billerService))
      phoneRef.forward(biller)

  }

}

