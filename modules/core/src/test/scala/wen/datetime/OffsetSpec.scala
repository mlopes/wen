package wen.datetime

import java.time.ZoneOffset

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._

class OffsetSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Offset" should {
    "be created from java.time.ZoneOffset" in forAll { zoneOffset: ZoneOffset =>
      val offset = Offset(zoneOffset)
      offset shouldBe a[Offset]
    }
  }

}
