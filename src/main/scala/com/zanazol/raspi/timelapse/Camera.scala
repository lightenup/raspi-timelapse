package com.zanazol.raspi.timelapse

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by andreas on 16/03/14.
 */
class Camera {
  private def pictureExtensionType = ".jpeg"

  def takePicture(): String = {
    val fileName = createFileName()
    executeShellCommand("raspistill -o " + fileName)

    fileName
  }

  private def executeShellCommand(command: String) {
    println(command)
    val runtime = Runtime.getRuntime
    val process = runtime.exec(command)
    process.waitFor()
  }

  private def createFileName(): String = {
    val sdf = new SimpleDateFormat("HH:mm-MMM-dd-yyyy")

    val resultDate = new Date(System.currentTimeMillis())
    val fileName = sdf.format(resultDate)
    fileName + pictureExtensionType

  }

}
