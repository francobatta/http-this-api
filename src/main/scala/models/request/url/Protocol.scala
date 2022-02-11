package com.francobatta.httpthis
package models.request.url

sealed trait Protocol

case object Http extends Protocol
case object Https extends Protocol