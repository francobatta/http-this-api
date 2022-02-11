package com.francobatta.httpthis
package models.request.url

object Ports {
  opaque type Port = Int
  object Port {
    def apply(port: Int, protocol: Protocol): Option[Port] = {
      Some(port)
    }

    extension (p: Port) {
      def toString = "Holaaa"
    }
  }
}