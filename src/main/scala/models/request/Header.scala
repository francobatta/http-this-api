package com.francobatta.httpthis
package models.request

sealed trait Header(name: String)(value: String)

case class ContentType(value: String) extends Header("Content-Type")(value)
