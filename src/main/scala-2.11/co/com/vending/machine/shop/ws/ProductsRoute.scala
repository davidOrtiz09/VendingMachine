package co.com.vending.machine.shop.ws

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.shop.entities.Product
import co.com.vending.machine.shop.storage.repository.ShopRepository

import scala.concurrent.Future
import scala.util.{Failure, Success}


case class ProductsRoute(vendingMachineRepo: ShopRepository , vendingMachineMaster: ActorRef)(implicit appConfig: AppConfig, val system: ActorSystem)
  extends Directives with JsonSupport {

  import system.dispatcher

  private val productRoute = appConfig.productRoute
  val route: Route = get {
      pathPrefix( productRoute / LongNumber) { code =>
       val posibleProduct:Future[Option[Product]] = vendingMachineRepo.getProductByCode(code.toString)
        onSuccess(posibleProduct) {
          case Some(item) => complete(item)
          case None       => complete(StatusCodes.custom(404 ,"PRODUCT NOT FOUND","PRODUCT NOT FOUND"))
        }
      }
    } ~
  get{
    path(productRoute){
      val allProducts = vendingMachineRepo.getAllProducts

     onComplete(allProducts){
        case Success(products) => complete(products)
        case Failure(err) =>  complete(StatusCodes.custom(404 ,"NOT PRODUCTS AVAILABLE","NOT PRODUCTS AVAILABLE"))
      }
    }
  }



}
