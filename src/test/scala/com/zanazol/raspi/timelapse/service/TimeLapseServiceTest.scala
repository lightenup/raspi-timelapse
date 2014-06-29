package com.zanazol.raspi.timelapse.service

import java.io.InputStream

import com.zanazol.raspi.timelapse.application.ComponentConfigurationModule
import com.zanazol.raspi.timelapse.resource.Camera
import fr.janalyse.ssh.SSHFtp
import org.specs2.mutable._
import org.specs2.mock.Mockito
import org.mockito.Matchers._

import scala.util.Try
import scalax.file.Path



/**
 * Created by andreas on 06/04/14.
 */
class TimeLapseServiceTest extends Specification with Mockito {

  "When a picture is taken the service" should {
    "be created at current relative path" in {
      ComponentConfigurationModule.modifyBindings{ implicit module =>
        import module._
        val result = createResult()
        val mockCamera = mock[Camera]
        mockCamera.takePicture returns result
        bind[Camera] toSingle mockCamera

        val mockFtp = mock[SSHFtp]
        bind[SSHFtp] toSingle mockFtp


        val service = new TimeLapseService()
        performCameraSideEffect(result.get)
        service.takeAndUploadPicture()

        there was one(mockCamera).takePicture()
        there was one(mockFtp).putFromStream(anyObject[InputStream], anyString)

        val resultingFile = Path(result.get)
        resultingFile.nonExistent must beTrue

      }

    }

    def createResult():Try[String] = {
      return Try("08:39:04-Jun-22-2014.jpeg")
    }

    def performCameraSideEffect(fileName:String)={
        val path: Path = Path.fromString(fileName)
        path.createFile()
    }
  }


}
