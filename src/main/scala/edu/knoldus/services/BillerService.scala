package edu.knoldus.services

import edu.knoldus.DatabaseRepo
import edu.knoldus.models.{Biller, Account}

class BillerService {

  def payAmt(account: Account, biller: Biller) = {
    DatabaseRepo.database += (account.accNo -> (account.copy(amount = account.amount - biller.amtPaid),
      List(biller.copy(amtPaid = biller.amtPaid, totalPaidAmt = biller.totalPaidAmt + biller.amtPaid))))
  }

}
