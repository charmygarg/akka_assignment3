package edu.knoldus.services

import edu.knoldus.DatabaseRepo
import edu.knoldus.models.Account

class SalaryDepositService {

  def addAmount(salary: Float, account: Account) = {
    DatabaseRepo.database += (account.accNo -> (account.copy(amount = account.amount + salary), Nil))
  }

}
