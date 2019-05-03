package wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}
import wen.test.Arbitraries._
import wen.test.Generators._
import wen.refine.refineHour
import wen.types.NumericTypes.NumericHour

class HourSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Hour" should {

    "be created with a value between 0 and 23" in forAll { hour: Option[Hour] =>
      hour should !==(None)
    }

    "fail to be created with an hour not between 0 and 23" in forAll(failedHourGen) { failedHour: Option[Hour] =>
      failedHour ===(None)
    }

    "creates a hour from a numeric hour" in forAll(hourAsIntGen) { hourAsInt: Int =>
      refineHour(hourAsInt) match {
        case Right(h: NumericHour) =>
          Hour(h) shouldBe a[Hour]
          Hour(h) should ===(Hour(hourAsInt).get)
        case _ => fail
      }
    }
  }
}
