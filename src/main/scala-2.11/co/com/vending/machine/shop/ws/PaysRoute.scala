package co.com.vending.machine.shop.ws

import akka.actor.{ActorRef, ActorSelection, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server._
import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.shop.entities.{PayRequest, PayResponse}

import scala.collection.JavaConversions._
import scala.concurrent.Future
import scala.util.{Failure, Success}
import akka.util.Timeout

import scala.concurrent.duration._


case class PaysRoute()(implicit appConfig: AppConfig, val system: ActorSystem)
  extends Directives with JsonSupport {


  private val payRoute = appConfig.payRoute
  private val productRoute = appConfig.productRoute
  private val currencyRules: List[Int] = appConfig.currencyRules.toList.map(_.toInt)
  private implicit val timeOut = Timeout(5.seconds)

  val route: Route = post {
    import akka.pattern.ask
    path(productRoute / payRoute) {
      entity(as[PayRequest]) { payReq =>
        val currencyValidation = currencyRules.find(_ == payReq.pay)
        currencyValidation match{
          case Some(res) => val ticketSelection = system.actorSelection(s"/user/${appConfig.vendingMachineMasterName}/${payReq.requestId}")
            val futurePayResponse: Future[PayResponse] = ask(ticketSelection, "ping").mapTo[PayResponse]
            onComplete(futurePayResponse){
              case Success(products) => complete(products)
              case Failure(err) =>  complete(StatusCodes.custom(404 ,"NOT PRODUCT REQUEST AVAILABLE","NOT PRODUCT REQUEST AVAILABLE"))
            }
          case None => complete(StatusCodes.custom(503 ,"CURRENCY VALUE INVALID","CURRENCY VALUE INVALID"))
        }
      }
    }
  }



}
