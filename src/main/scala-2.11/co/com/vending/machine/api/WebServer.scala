package co.com.vending.machine.api

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import co.com.vending.machine.commons.api.CorsSupport
import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.commons.data.db.DBCore
import co.com.vending.machine.core.{ActorsCore, BootedCore, Core}
import co.com.vending.machine.shop.storage.repository.ShopRepository



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
case class WebServer(vendingMachineRepo: ShopRepository , vendingMachineMaster: ActorRef)(implicit system: ActorSystem, val appConfig: AppConfig) extends CorsSupport {


  val host = appConfig.webServerHost

  val port = appConfig.webServerPort

  implicit private val actorMat: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(system))

  /*private val routes = Seq(
    //PersonaNaturalRoute(consultasSoiRepo, consultasPublisherPath), PersonaJuridicaRoute(consultasSoiRepo, consultasPublisherPath),
    //MassivePersonaNaturalService(consultasMasivasMasterProxy), MassivePersonaJuridicaService(consultasMasivasMasterProxy)
  ).map(_.route).reduceLeft(_ ~ _)

  Http().bindAndHandle(corsHandler(routes), host, port)*/
}
