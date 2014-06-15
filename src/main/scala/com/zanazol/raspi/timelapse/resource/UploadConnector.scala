package com.zanazol.raspi.timelapse.resource


/**
 * Created by andreas on 15/06/14.
 */
trait UploadConnector {

  def uploadFile(relativeFilePath: String)

}
