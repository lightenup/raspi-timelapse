package com.zanazol.raspi.timelapse.resource

import scala.util.Try

/**
 * Created by andreas on 23/04/14.
 */
trait Camera {
  def takePicture(): Try[String]
}
