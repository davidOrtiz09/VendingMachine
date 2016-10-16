package co.com.vending.machine.commons.api

import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import co.com.vending.machine.commons.config.AppConfig

trait CorsSupport {

  implicit def appConfig: AppConfig

  def corsHandler(r: Route): Route = {
    addAccessControlHeaders {
      preFlightRequestHandler ~ r
    }
  }

  //this directive adds access control headers to normal responses
  private def addAccessControlHeaders: Directive0 = {
    respondWithHeaders(
      `Access-Control-Allow-Origin`.`*`,
      `Access-Control-Allow-Credentials`(true),
      `Access-Control-Allow-Headers`("Origin", "Authorization", "Content-Type", "X-Requested-With")
    )
  }

  //this handles preflight OPTIONS requests.
  private def preFlightRequestHandler: Route = {
    options {
      complete(HttpResponse(StatusCodes.OK).withHeaders(`Access-Control-Allow-Methods`(OPTIONS, POST, PUT, GET, DELETE)))
    }
  }
}
