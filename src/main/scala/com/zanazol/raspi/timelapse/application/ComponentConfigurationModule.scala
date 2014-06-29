package com.zanazol.raspi.timelapse.application

import com.escalatesoft.subcut.inject.NewBindingModule
import com.zanazol.raspi.timelapse.resource._
import fr.janalyse.ssh.{SSHFtp, SSHOptions, SSH}

/**
 * Created by andreas on 23/03/14.
 */
object ComponentConfigurationModule extends  NewBindingModule (module => {
  import module._
  val appConfig = new ApplicationConfig

  bind[CommandLineExecutor] toSingle new UnixCommandLineExecutor
  bind[Camera] toProvider { implicit bindingModule => new RaspberryPiCamera()}
  bind[CommandLineExecutor] toProvider {implicit bindingModule => new UnixCommandLineExecutor}
  bind[SSHFtp] toProvider {
    implicit val ssh = new SSH(SSHOptions(appConfig.ftpHost, appConfig.ftpUser, appConfig.ftpPassword))
    val sshFtp = new SSHFtp()
    sshFtp
  }

})
