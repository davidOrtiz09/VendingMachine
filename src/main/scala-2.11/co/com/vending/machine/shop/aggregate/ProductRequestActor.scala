package co.com.vending.machine.shop.aggregate

import akka.actor.{Actor, ActorLogging, Props}


/**
  * Actor's factory  [ProductRequestActor]
  */
object ProductRequestActor{


  def props(productCost: Int ,  productName:String) = Props(ProductRequestActor(productCost , productName))
}

case class ProductRequestActor(productCost: Int , productName:String) extends Actor with ActorLogging {


  override def receive: Receive = {
    case _ => log.info("Unknown message !")
  }

  override def preStart():Unit ={
    log.info(s"Me the actor ${self.path.name}. I'm Alive !")
  }


}
