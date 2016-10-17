package co.com.vending.machine.aggregate

import java.util.UUID

import akka.actor.{Actor, ActorLogging, Props}
import co.com.vending.machine.aggregate.VendingMachineMaster.SelectProduct
import co.com.vending.machine.shop.aggregate.ProductRequestActor
import co.com.vending.machine.shop.storage.repository.ShopRepository


/**
  * Actor's factory  [VendingMachineMaster]
  */
object VendingMachineMaster{


  case class SelectProduct(code:String , productCost:Int , productName:String)
  def props(vendingRepo: ShopRepository) = Props(VendingMachineMaster(vendingRepo))
}


case class VendingMachineMaster(vendingRepo: ShopRepository) extends Actor with ActorLogging {

  override def receive: Receive = {
    case  msg: SelectProduct  => process(msg)
    case _ => log.info("Unknown message !")
  }


  private def process(msg:SelectProduct) = {
    import msg._

 context.actorOf(ProductRequestActor.props(productCost , productName ), code)

  }
}
