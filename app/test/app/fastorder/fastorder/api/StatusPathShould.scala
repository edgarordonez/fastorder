package app.fastorder.fastorder.api

import akka.http.scaladsl.model._
import app.fastorder.HttpSpec

final class StatusPathShould extends HttpSpec {
  "Responds ok at requesting the status" in getting("/status") {
    status shouldBe StatusCodes.OK
    contentType shouldBe ContentTypes.`application/json`
    entityAs[String] shouldBe """{"status":"ok"}"""
  }
}
