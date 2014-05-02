package com.zanazol.raspi.timelapse

import com.zanazol.raspi.timelapse.service.TimeLapseService

/**
 * Created by andreas on 23/03/14.
 */
object TimeLapseBoot {

  def main(args: Array[String]) {
    implicit val bindingModule = ComponentConfigurationModule
    val service = new TimeLapseService()
    service.takeAndUploadPicture()
  }

}
