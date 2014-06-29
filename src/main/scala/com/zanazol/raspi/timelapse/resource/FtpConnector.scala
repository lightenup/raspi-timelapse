package com.zanazol.raspi.timelapse.resource

import java.io.{FileInputStream, InputStream}
import com.escalatesoft.subcut.inject.{BindingModule, Injectable}
import com.zanazol.raspi.timelapse.application.ApplicationConfigHelper
import fr.janalyse.ssh.{SSHFtp, SSHOptions, SSH}


/**
 * Created by andreas on 15/06/14.
 */
class FtpConnector(implicit val bindingModule: BindingModule) extends UploadConnector with Injectable with ApplicationConfigHelper{
  val ftp = injectOptional[SSHFtp] getOrElse {implicit val ssh = new SSH(SSHOptions(ftpHost, ftpUser, ftpPassword))
  val sshFtp = new SSHFtp()
  sshFtp}

  def uploadFile(relativeFilePath: String) = {
      val inputStream: InputStream = new FileInputStream(relativeFilePath)
      ftp.putFromStream(inputStream, relativeFilePath)
  }
}
