package com.zanazol.raspi.timelapse.resource

/**
 * Created by andreas on 02/05/14.
 */
class UnixCommandLineExecutor extends CommandLineExecutor{

  def executeShellCommand(command: String) = {
    val runtime = Runtime.getRuntime
    val process = runtime.exec(command)
    process.waitFor()
  }


}
