package com.francobatta.httpthis
package validations.request

import models.request.url.{Protocol, RelativeUrl}

import validations.generic.GenericValidation

sealed trait UrlValidation extends GenericValidation

case object InvalidRelativeUrl extends UrlValidation {
  def errorMessage: String = s"Invalid relative URL"
}

case class InvalidProtocol(invalidProtocol: String) extends UrlValidation {
  def errorMessage: String = s"Invalid protocol ${invalidProtocol} passed. Accepted values: http, https" // TODO reflection accepted values
}

case class InvalidPort(invalidPort: Int, invalidProtocol: Option[String] = None) extends UrlValidation {
  def errorMessage: String = s"Invalid port ${invalidPort} passed" + invalidProtocol.fold("")(invalidProtocol => s". No port value matches protocol ${invalidProtocol}")
}

case class InvalidDomain(invalidDomain: String) extends UrlValidation {
  def errorMessage: String = s"Invalid domain ${invalidDomain} passed"
}