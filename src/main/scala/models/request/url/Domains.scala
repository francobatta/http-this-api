package com.francobatta.httpthis
package models.request.url

object Domains {
  opaque type Domain = String
  object Domain {
    def apply(s: String): Option[Domain] = {
      Some(s)
    }

    extension (p: Domain) {
      def toString = "Holaaa"
    } 
  }
}