package com.zanazol.raspi.timelapse.application

import com.escalatesoft.subcut.inject.NewBindingModule
import com.zanazol.raspi.timelapse.resource._

/**
 * Created by andreas on 23/03/14.
 */
object ComponentConfigurationModule extends NewBindingModule(module => {
  import module._

  bind[CommandLineExecutor] toSingle new UnixCommandLineExecutor
  bind[Camera] toProvider { implicit bindingModule => new RaspberryPiCamera()}

})
