package co.com.vending.machine.commons.config

import com.typesafe.config.Config

/**
  * Load All the personalized configurations of the system
  */
case class AppConfig(config:Config) {


 lazy  val  dataBaseFileName = config.getString("db.fileName")

 lazy val webServerHost = config.getString("ws.host")

 lazy val webServerPort = config.getInt("ws.port")

 lazy val  vendingMachineMasterName = config.getString("Actors.vending-machine-master")


}
