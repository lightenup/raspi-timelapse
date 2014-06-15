package com.zanazol.raspi.timelapse.resource

import java.text.SimpleDateFormat
import java.util.Date

import com.zanazol.raspi.timelapse.application.ComponentConfigurationModule
import org.scalatest.PrivateMethodTester
import org.specs2.mutable.Specification


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

      val expectedDate = sdf.format(resultDate)

      expectedDate.equals(timePart) must beTrue
    }
  }

}
