package com.zanazol.raspi.timelapse.resource

/**
 * Created by andreas on 02/05/14.
 */
trait CommandLineExecutor {
  def executeShellCommand(command: String)
}
