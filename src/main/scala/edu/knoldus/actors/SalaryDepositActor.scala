package edu.knoldus.actors

import akka.actor.{ActorLogging, Actor}
import edu.knoldus.DatabaseRepo
import edu.knoldus.models.Account
import edu.knoldus.services.SalaryDepositService

class SalaryDepositActor(salaryDepositService: SalaryDepositService) extends Actor with ActorLogging {

  override def receive = {
    case account: Account =>
      self ! depositSalary(account.amount, account, account.username)
  }

  def depositSalary(amount: Float, account: Account, username: String) = {
    val user = DatabaseRepo.database(account.accNo)
    if(account.username.contains(user._1.username)) {
      sender() ! salaryDepositService.addAmount(amount, account)
      log.info("Salary has been added to account")
    } else {
      log.error("User doesn't exists")
    }
  }

}
