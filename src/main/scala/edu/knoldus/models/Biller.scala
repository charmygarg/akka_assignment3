package edu.knoldus.models

case class Biller(category: String, billerName: String, accNo: Long, date: String, amtPaid: Float,
                  totalPaidAmt: Float = 0, itr: Iterations)
