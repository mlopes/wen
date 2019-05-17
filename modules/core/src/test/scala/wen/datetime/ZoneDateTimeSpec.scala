package wen.datetime

import java.time.{Instant, OffsetDateTime}

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._

class ZoneDateTimeSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "ZoneDateTime" should {
    "be created from java.time.Instant" in forAll { instant: Instant =>
      val zoneDateTime = ZoneDateTime(instant)
      zoneDateTime shouldBe a[ZoneDateTime]
    }

    "be created from java.time.OffsetDateTime" in forAll { offsetDateTime: OffsetDateTime =>
      val zoneDateTime = ZoneDateTime(offsetDateTime)
      zoneDateTime shouldBe a[ZoneDateTime]

    }
  }
}
