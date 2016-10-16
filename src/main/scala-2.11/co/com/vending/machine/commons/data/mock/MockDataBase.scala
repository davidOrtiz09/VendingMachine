package co.com.vending.machine.commons.data.mock

import co.com.vending.machine.shop.storage.dto.ProductDTO

import scala.util.{Failure, Success, Try}

/**
  * Çlass thats load mock data from a file
  */
case class MockDataBase (fileName: String) {

  /**
    * Load the mock data from a file
    * @return
    */
  def loadAllItems = {
   val loadData =  loadMockDataFile(fileName)
    loadData match{
      case Success(dtoList) => dtoList
      case Failure(err) => List()
    }

  }

  /**
    * Load de data file
    * @param fileNameMock: Name of the data file
    * @return
    */
  private def loadMockDataFile(fileNameMock:String) = {
    Try {
  val source = scala.io.Source.fromFile(fileNameMock)
  val lines = source.getLines().toList
      convertLinesToDTO(lines)
      }
  }

  /**
    * Convert all the lines of the file in ProductDTO
    * @param lines: All the lines of the file
    * @return
    */
  private def convertLinesToDTO(lines:List[String]):List[ProductDTO] = {
      lines.map { elem =>
        val array = elem.split(",")
        ProductDTO(array(0), array(1), array(2).toInt)
      }

  }






}
