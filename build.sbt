

name := "VendingMachine"

version := "1.0"

scalaVersion := "2.11.8"




libraryDependencies ++= {
  import Dependencies._
  vendingMachineLibs ++ testLibs
}