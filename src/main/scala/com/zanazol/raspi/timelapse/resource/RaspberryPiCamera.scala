package com.zanazol.raspi.timelapse.resource

import java.text.SimpleDateFormat
import java.util.Date

import com.escalatesoft.subcut.inject.{BindingModule, Injectable}

import scala.util.Try

/**
 * Created by andreas on 16/03/14.
 */
class RaspberryPiCamera(implicit val bindingModule: BindingModule) extends Camera with Injectable {
  val commandLine = injectOptional[CommandLineExecutor] getOrElse (new UnixCommandLineExecutor)
  val pictureExtensionType = "jpeg"
  val dateFormat = "HH:mm:ss-MMM-dd-yyyy"

  def takePicture(): Try[String] = {
    Try {
      val fileName = createFileName(pictureExtensionType, System.currentTimeMillis())
      commandLine.executeShellCommand("raspistill -o " + fileName)
      fileName
    }
  }

  private def createFileName(fileExtension: String, currentTimeMillis: Long): String = {
    val sdf = new SimpleDateFormat(dateFormat)

    val resultDate = new Date(currentTimeMillis)
    val fileName = sdf.format(resultDate)
    fileName + "." + fileExtension
  }

}
