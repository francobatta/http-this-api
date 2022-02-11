package com.francobatta.httpthis
package validators.request

import models.request.url.{AbsoluteUrl, Asterisk, Http, Https, Parameter, Protocol, RelativeUrl}
import models.request.url.Domains.*
import models.request.url.Paths.*
import models.request.url.Ports.*
import validations.request.*

import cats.data.*
import cats.data.Validated.*
import cats.implicits.*

sealed trait UrlValidator {

  type ValidationResult[A] = ValidatedNec[UrlValidation, A]

  private def validateProtocol(protocol: String): ValidationResult[Protocol] = protocol.toLowerCase match {
    case "http" => Http.validNec
    case "https" => Https.validNec
    case _ => InvalidProtocol(protocol).invalidNec
  }

  private def validateDomain(domain: String): ValidationResult[Domain] = Domain(domain).fold(InvalidDomain(domain).invalidNec)(_.validNec)

  private def validatePort(port: Int, validatedProtocol: ValidationResult[Protocol]): ValidationResult[Port] = validatedProtocol match {
    case Valid(protocol) => Port(port, protocol).fold(InvalidPort(port).invalidNec)(_.validNec)
    case Invalid(invalidProtocol) => InvalidPort(port, Some(invalidProtocol.head.errorMessage)).invalidNec
  }

  private def validatePath(path: String) = Path(path).validNec

  private def validateParameters(maybeParameters: Option[String]) = maybeParameters.map(parameters => List(Parameter(parameters, parameters))).validNec

  /**
   * @param protocol an unvalidated protocol input (i.e. http, https)
   * @param domain an unvalidated domain input
   * @param port an unvalidated port input (must be correct according to port limit and protocol)
   * @param relativeUrl
   * @return a Valid [[AbsoluteUrl]], or a collection of invalid parameters under [[UrlValidation]]
   */
  def validateAbsoluteUrl(protocol: String, domain: String, port: Int, validatedRelativeUrl: ValidationResult[RelativeUrl]): ValidationResult[AbsoluteUrl] = {
    val validatedProtocol = validateProtocol(protocol)
    (validatedProtocol, validateDomain(domain), validatePort(port, validatedProtocol), validatedRelativeUrl)
      .mapN(AbsoluteUrl)
  }
  
  def validateRelativeUrl(path: String, parameters: Option[String]): ValidationResult[RelativeUrl] = {
    (validatePath(path), validateParameters(parameters))
      .mapN(RelativeUrl)
  }

  /**
   * @return A valid [[Asterisk]] - asterisk URLs are always valid and point to the server
   */
  def validateAsterisk = Validated.Valid(Asterisk)
}

object UrlValidator extends UrlValidator