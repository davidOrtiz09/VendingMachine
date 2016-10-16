package co.com.vending.machine.commons.config

import com.typesafe.config.Config

/**
  * Load All the personalized configurations of the system
  */
case class AppConfig(config:Config) {


 lazy  val  dataBaseFileName = config.getString("db.fileName")

}
