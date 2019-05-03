package wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._
import wen.test.Generators._
import wen.refine.refineMinute
import wen.types.NumericTypes.NumericMinute

class MinuteSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Minute" should {

    "be created with a value between 0 and 59" in forAll { minute: Option[Minute] =>
      minute should !==(None)
    }

    "fail to be created with an Minute not between 0 and 59" in forAll(failedMinuteGen) { failedMinute: Option[Minute] =>
      failedMinute should ===(None)
    }

    "creates a minute from a numeric minute" in forAll(minuteAsIntGen) { minuteAsInt: Int =>

      refineMinute(minuteAsInt) match {
        case Right(m: NumericMinute) =>
          Minute(m) shouldBe a[Minute]
          Minute(m) should ===(Minute(minuteAsInt).get)
        case _ => fail
      }
    }
  }
}
