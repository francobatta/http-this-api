package com.francobatta.httpthis
package models.request

sealed trait Method(safe: Boolean, idempotent: Boolean)

case object Delete extends Method(false, true)
case object Get extends Method(true, true)
case object Head extends Method(true, true)
case object Options extends Method(true, true)
case object Patch extends Method(false, true)
case object Post extends Method(false, false)
case object Put extends Method(false, true)