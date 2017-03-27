package edu.knoldus

import akka.actor.{Props, ActorSystem}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import edu.knoldus.actors._
import edu.knoldus.models.{Iterations, Biller, Account}
import edu.knoldus.services.{BillerService, SalaryDepositService, UserAccountService}
import scala.concurrent.duration._
import akka.pattern.ask

object Main extends App {

  val accountService = new UserAccountService
  val conf = ConfigFactory.load()
  val system = ActorSystem("AccountGenerator", conf)
  val accountGeneratorRef = system.actorOf(Props(classOf[AccountGeneratorActor], accountService))

  implicit val timeout = Timeout(1000 seconds)
  import scala.concurrent.ExecutionContext.Implicits.global

  val account = Account(87687979L, "Simar", "Delhi", "ksimar", 8000)
  val biller = Biller("car", "Simar", 87687979L, "2/1/2017", 200, 0, Iterations(0,0))

  val result0 = accountGeneratorRef ? account
  result0 map println

  val salaryDepositService = new SalaryDepositService

  val salaryDepositRef = system.actorOf(Props(classOf[SalaryDepositActor], salaryDepositService))
  val result2 = salaryDepositRef ? account
  result2 map println

  val billerService = new BillerService
  val billProcessorRef = system.actorOf(Props(classOf[BillProcessorActor], billerService, account, biller))
  val result3 = billProcessorRef ? biller.category
  result3 map println

  val reportGeneratorRef = system.actorOf(Props(classOf[ReportGeneratorActor], result3))
  val scheduler = system.actorOf(Props(classOf[Scheduler], reportGeneratorRef, system))
  scheduler ! account

}
