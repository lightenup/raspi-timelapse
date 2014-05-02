package com.zanazol.raspi.timelapse.resource

import org.specs2.mutable.Specification
import org.scalatest.PrivateMethodTester
import com.zanazol.raspi.timelapse.ComponentConfigurationModule
import java.util.{Calendar, Date}
import java.text.SimpleDateFormat


/**
 * Created by andreas on 02/05/14.
 */
class RaspberryPiCameraSpec extends Specification with PrivateMethodTester {
  val createFileName = PrivateMethod[String]('createFileName)
  val fileExtensionType = "jpg"
  implicit val bindingModule = ComponentConfigurationModule
  val camera = new RaspberryPiCamera()

  "When I create a new file name, the name " should {
    "contain a file extension" in {
      val fileName = camera invokePrivate createFileName(fileExtensionType, System.currentTimeMillis())
      val fileNameParts = fileName.split("\\.")

      fileNameParts.size must_== 2
      fileNameParts(1).equals(fileExtensionType) must beTrue

    }
    "contain a timestamp" in {
      val currentTime = System.currentTimeMillis()
      val fileName = camera invokePrivate createFileName(fileExtensionType, currentTime)

      val fileNameParts = fileName.split("\\.")
      val timePart = fileNameParts(0)

      val sdf = new SimpleDateFormat(camera.dateFormat)
      val resultDate = new Date(currentTime)

      val expedtedDate = sdf.format(resultDate)

      expedtedDate.equals(timePart) must beTrue
    }
  }

}
