package co.com.vending.machine.aggregate

import akka.actor.{Actor, ActorLogging, Props}
import co.com.vending.machine.shop.storage.repository.ShopRepository


/**
  * Actor's factory  [VendingMachineMaster]
  */
object VendingMachineMaster{
  def props(vendingRepo: ShopRepository) = Props(VendingMachineMaster(vendingRepo))
}


case class VendingMachineMaster(vendingRepo: ShopRepository) extends Actor with ActorLogging {

  override def receive: Receive = ???
}
