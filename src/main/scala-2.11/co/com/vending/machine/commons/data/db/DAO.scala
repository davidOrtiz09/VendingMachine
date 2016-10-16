package co.com.vending.machine.commons.data.db

import scala.concurrent.Future

/**
  * DAO Abstraction
  */
trait DAO[A , B] {

  def findAll:Future[Seq[B]]

  def findById(id: A):Future[Option[B]]

}
