package com.francobatta.httpthis
package models.request.url

import models.request.url.Domains.Domain
import models.request.url.Paths.Path
import models.request.url.Ports.Port
import models.request.url.Protocol
import validations.request.{InvalidRelativeUrl, UrlValidation}

import cats.data.*
import cats.implicits.*
import validators.request.UrlValidator

sealed trait Url

case object Asterisk extends Url
case class AbsoluteUrl(protocol: Protocol, domain: Domain, port: Port, relativeUrl: RelativeUrl) extends Url
case class RelativeUrl(path: Path, parameters: Option[List[Parameter]]) extends Url

object Url {
  def from(input: String): ValidatedNec[UrlValidation, Url] = input match {
    case "*" => UrlValidator.validateAsterisk
    //case maybeRelativeUrl if input.startsWith("/") => validateRelativeUrl(maybeRelativeUrl)
    case maybeAbsoluteUrl => UrlValidator.validateAbsoluteUrl("http", "asd", 80, RelativeUrl(Path(""), None).validNec)
  }
}