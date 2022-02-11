package com.francobatta.httpthis
package models.request.url

object Paths {
  opaque type Path = String
  object Path {
    def apply(s: String): Path = {
      s
    }

    extension (p: Path) {
      def toString = "Holaaa"
    }
  }
}