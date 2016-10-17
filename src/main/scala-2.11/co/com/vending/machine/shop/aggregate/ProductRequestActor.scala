package co.com.vending.machine.shop.aggregate

import akka.actor.{Actor, ActorLogging, PoisonPill, Props}
import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.shop.aggregate.ProductRequestActor.{CancelOrder, Finish, Pay}
import co.com.vending.machine.shop.entities.{CancelResposne, PayResponse}


/**
  * Actor's factory  [ProductRequestActor]
  */
object ProductRequestActor{

  case class Pay(value:Int)
  case object Finish
  case object CancelOrder


  def props(productCost: Int ,  productName:String)(implicit appConfig: AppConfig) = Props(ProductRequestActor(productCost , productName))
}

case class ProductRequestActor(productCost: Int , productName:String)(implicit appConfig:AppConfig) extends Actor with ActorLogging {

  var paySoFar = 0


  override def receive: Receive = {
    case msg:Pay => process(msg)
    case Finish => self ! PoisonPill
    case CancelOrder => sender() ! CancelResposne("Here is your money back !" , paySoFar)
                         self ! Finish
    case _ => log.info("Unknown message !")
  }

  private def process(msg:Pay) = {
    import msg._
    paySoFar = paySoFar + value
    val name = self.path.name
    val debt = productCost - paySoFar

    log.info(" Me the vending machine I receive : " + value + " coins")

    if(paySoFar < productCost) sender ! PayResponse(name,debt ,appConfig.payNotEnough ,0 ) else {
      sender ! PayResponse(name, 0 , appConfig.paySuccess + " " + productName , debt * (-1))
      self ! Finish
    }

  }

  override def preStart():Unit ={
    log.info(s"Me the actor ${self.path.name}. I'm Alive !")
  }

  override def  postStop(): Unit = {
    log.info(s"Me the actor ${self.path.name}. I kill my self !")
  }


}
