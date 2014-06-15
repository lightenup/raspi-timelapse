package com.zanazol.raspi.timelapse.resource

import java.io.{FileInputStream, InputStream}

import com.zanazol.raspi.timelapse.application.ApplicationConfig
import fr.janalyse.ssh.SSH


/**
 * Created by andreas on 15/06/14.
 */
class FtpConnector extends UploadConnector with ApplicationConfig {

  def uploadFile(relativeFilePath: String) = {
    SSH.ftp(ftpHost, ftpUser, ftpPassword) { ftp =>
      val inputStream: InputStream = new FileInputStream(relativeFilePath)
      ftp.putFromStream(inputStream, relativeFilePath)
    }

  }
}
