package com.zanazol.raspi.timelapse.service

import com.escalatesoft.subcut.inject.{BindingModule, Injectable}
import com.zanazol.raspi.timelapse.resource._

import scala.util.{Failure, Success}


/**
 * Created by andreas on 16/03/14.
 */
class TimeLapseService(implicit val bindingModule: BindingModule) extends Injectable {
  val camera = injectOptional [Camera] getOrElse(new RaspberryPiCamera())
  val upload = new FtpConnector
  val commandLine = injectOptional[CommandLineExecutor] getOrElse (new UnixCommandLineExecutor)

  def takeAndUploadPicture() {

    camera.takePicture() match {
      case Success(pictureName) => {
        upload.uploadFile(pictureName)
        commandLine.executeShellCommand(s"rm $pictureName")
      }
      case Failure(ex) => println(s"Oooops...$ex")
    }

  }


}
