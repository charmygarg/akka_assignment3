package edu.knoldus.services

import edu.knoldus.DatabaseRepo
import edu.knoldus.models.Account

class UserAccountService {

  val accountInfo = Account(768574657L, "Charmy", "Noida", "charmygarg", 4000)

  def addUser(account: Account) = {
    DatabaseRepo.list += account
    DatabaseRepo.list += accountInfo
  }

}
