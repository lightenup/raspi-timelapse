package com.zanazol.raspi.timelapse.service

import com.zanazol.raspi.timelapse.resource.{Camera, RaspberryPiCamera}
import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import scala.util.{Failure, Success}


/**
 * Created by andreas on 16/03/14.
 */
class TimeLapseService(implicit val bindingModule: BindingModule) extends Injectable {
  val camera = new RaspberryPiCamera()

  def takeAndUploadPicture() {

     camera.takePicture() match{
       case Success (pictureName) => println("The pictures named " + pictureName)
       case Failure (ex) => println(s"Oooops...$ex")
     }


    //upload picture to server
    //remove local copy of picture
  }


}
