package com.zanazol.raspi.timelapse

import com.escalatesoft.subcut.inject.NewBindingModule
import com.zanazol.raspi.timelapse.resource.{Camera, RaspberryPiCamera}

/**
 * Created by andreas on 23/03/14.
 */
object ComponentConfigurationModule extends NewBindingModule(module => {
  import module._

  bind[Camera] toSingle new RaspberryPiCamera

})
