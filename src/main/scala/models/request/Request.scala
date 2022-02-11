package com.francobatta.httpthis
package models.request

import models.request.url.Url

case class Request(url: Url, method: Method, headers: List[Header], Body: String)