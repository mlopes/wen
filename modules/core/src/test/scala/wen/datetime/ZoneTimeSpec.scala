package wen.datetime

import java.time.OffsetTime

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._

class ZoneTimeSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Zone Time" should {
    "be constructed from java.time.OffsetTime" in forAll { offsetTime: OffsetTime =>
      val zoneTime = ZoneTime(offsetTime)
      zoneTime shouldBe a[ZoneTime]
    }
  }
}
