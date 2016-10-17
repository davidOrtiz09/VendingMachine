package co.com.vending.machine.api

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.commons.data.db.DBCore
import co.com.vending.machine.core.{ActorsCore, BootedCore, Core}
import co.com.vending.machine.shop.storage.repository.ShopRepository
import co.com.vending.machine.shop.ws.ProductsRoute



/**
  * Define app's web server using akka-http
  */
trait ApiCore {
  this: Core with BootedCore with DBCore with ActorsCore =>

      /**
        * App's web server.
        */
  WebServer(vendingMachineRepository , vendingMachineMaster)

}

/**
  * Web server that receive all the requests of the vending machine
  */
case class WebServer(vendingMachineRepo: ShopRepository , vendingMachineMaster: ActorRef)(implicit system: ActorSystem, val appConfig: AppConfig)  {


  val host = appConfig.webServerHost

  val port = appConfig.webServerPort

  implicit private val actorMat: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(system))

  private val routes = Seq(
    ProductsRoute(vendingMachineRepo , vendingMachineMaster)
  ).map(_.route).reduceLeft(_ ~ _)

  println(s"Server online at http://$host:$port ...")
  Http().bindAndHandle(routes, host, port)
}
