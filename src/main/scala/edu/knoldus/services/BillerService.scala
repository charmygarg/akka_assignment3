package edu.knoldus.services

import edu.knoldus.models.Biller

class BillerService {

  def payAmt(amt: Float, biller: Biller): Biller = {
    biller.copy(amtPaid = biller.amtPaid - amt)
  }

}
