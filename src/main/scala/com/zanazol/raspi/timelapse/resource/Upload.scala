package com.zanazol.raspi.timelapse.resource

import com.decodified.scalassh.SshClient
import net.schmizz.sshj.xfer.{TransferListener, LoggingTransferListener}
import net.schmizz.sshj.xfer.scp.SCPFileTransfer
import net.schmizz.sshj.sftp.SFTPClient

/**
 * Created by andreas on 06/04/14.
 */
class Upload {
  self: SshClient =>

  def sftp[T](fun: SFTPClient => T) = {
    authenticatedClient.right.flatMap {
      client =>
        startSession(client).right.flatMap {
          session =>
            protect("SFTP client failed") {
              val ftpClient = client.newSFTPClient()
              try {
                fun(ftpClient)
              } finally {
                ftpClient.close()
              }
            }
        }
    }
  }

  def fileTransfer(fun: SCPFileTransfer => Unit)(implicit listener: TransferListener = new LoggingTransferListener()) = {
    authenticatedClient.right.flatMap {
      client =>
        startSession(client).right.flatMap {
          session =>
            protect("SCP file transfer failed") {
              val transfer = client.newSCPFileTransfer()
              transfer.setTransferListener(listener)
              fun(transfer)
              this
            }
        }
    }
  }

  def upload(localPath: String, remotePath: String)(implicit listener: TransferListener = new LoggingTransferListener()) = {
    fileTransfer(_.upload(localPath, remotePath))(listener)
  }
}
