package com.zanazol.raspi.timelapse

import com.escalatesoft.subcut.inject.NewBindingModule
import com.zanazol.raspi.timelapse.resource.{UnixCommandLineExecutor, CommandLineExecutor, Camera, RaspberryPiCamera}

/**
 * Created by andreas on 23/03/14.
 */
object ComponentConfigurationModule extends NewBindingModule(module => {
  import module._

  bind[CommandLineExecutor] toSingle {new UnixCommandLineExecutor}

})
