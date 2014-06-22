package com.zanazol.raspi.timelapse.service

import com.escalatesoft.subcut.inject.{BindingModule, Injectable}
import com.zanazol.raspi.timelapse.resource.{Camera, FtpConnector, RaspberryPiCamera}

import scala.util.{Failure, Success}


/**
 * Created by andreas on 16/03/14.
 */
class TimeLapseService(implicit val bindingModule: BindingModule) extends Injectable {
  val camera = injectOptional [Camera] getOrElse(new RaspberryPiCamera())
  val upload = new FtpConnector

  def takeAndUploadPicture() {

    camera.takePicture() match {
      case Success(pictureName) => {
        upload.uploadFile(pictureName)
      }
      case Failure(ex) => println(s"Oooops...$ex")
    }

    //remove local copy of picture
  }


}
