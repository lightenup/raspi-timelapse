package com.zanazol.raspi.timelapse.application

import com.typesafe.config.ConfigFactory

/**
 * Created by andreas on 15/06/14.
 */
trait ApplicationConfig {
  val conf = ConfigFactory.load()
  val ftpHost = conf.getString("ftp.host")
  val ftpUser = conf.getString("ftp.user")
  val ftpPassword = conf.getString("ftp.password")

}
