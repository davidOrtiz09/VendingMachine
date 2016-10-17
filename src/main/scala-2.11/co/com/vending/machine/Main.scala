package co.com.vending.machine

import co.com.vending.machine.core._
import co.com.vending.machine.commons.data.db._
import co.com.vending.machine.api.ApiCore

/**
  * Main object to start up the application
  */
object Main extends App with Core with BootedCore with DBCore with ActorsCore with ApiCore