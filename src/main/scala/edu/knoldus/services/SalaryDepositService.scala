package edu.knoldus.services

import edu.knoldus.models.Account

class SalaryDepositService {

  def addAmount(salary: Float, account: Account): Account = {
    account.copy(amount = account.amount + salary)
  }

}
