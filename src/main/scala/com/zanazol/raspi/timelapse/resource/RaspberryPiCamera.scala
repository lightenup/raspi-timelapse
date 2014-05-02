package com.zanazol.raspi.timelapse.resource

import java.text.SimpleDateFormat
import java.util.Date
import scala.util.{Failure, Success, Try}

/**
 * Created by andreas on 16/03/14.
 */
class RaspberryPiCamera extends Camera{
  private def pictureExtensionType = ".jpeg"

  def takePicture(): Try[String] = {
    Try {
      createFileName() match {
        case Success(fileName) => {
          executeShellCommand("raspistill -o " + fileName)
          return Success(fileName)
        }
        case Failure(ex) => return Failure(ex)

      }
    }

  }

  private def executeShellCommand(command: String) {
    val runtime = Runtime.getRuntime
    val process = runtime.exec(command)
    process.waitFor()
  }

  private def createFileName(): Try[String] = {
    Try {
      val sdf = new SimpleDateFormat("HH:mm-MMM-dd-yyyy")

      val resultDate = new Date(System.currentTimeMillis())
      val fileName = sdf.format(resultDate)
      fileName + pictureExtensionType
    }
  }

}
