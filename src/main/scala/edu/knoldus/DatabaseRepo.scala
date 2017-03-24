package edu.knoldus

import edu.knoldus.models.{Biller, Account}

import scala.collection.mutable

object DatabaseRepo {

  val database: mutable.Map[Long, (Account, List[Biller])] = mutable.Map()

}
