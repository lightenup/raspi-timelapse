package com.zanazol.raspi.timelapse.resource

import java.io.{FileInputStream, InputStream}

import fr.janalyse.ssh.SSH
import org.specs2.mutable.Specification


/**
 * Created by andreas on 06/06/14.
 */
class FoolingAroundWithSshTest extends Specification {

  "When I use the SSH library it" should {
    "be able to log on to a remote server and list the folder structure" in {
      val results = SSH.once("192.168.1.100", "andreas", "mus18yen") {
        _ executeAndTrimSplit "find /usr/share -name '*.jpg'"
      }

      for (result <- results) {
        print(s"result: $result")
      }

      SSH.ftp("192.168.1.100", "andreas", "mus18yen") { ftp =>
        ftp.put("Mine data some en string. Skal skrives til en fil som heter ..", "ftp_test.txt")

        val path = "first.jpg"
        val inputStream: InputStream = new FileInputStream(path)
        ftp.putFromStream(inputStream, "some.jpg")
      }

      true must beTrue

    }
  }


}
