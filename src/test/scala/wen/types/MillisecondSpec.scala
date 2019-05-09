package wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._
import wen.test.Generators._
import wen.refine.refineMillisecond
import wen.types.NumericTypes.NumericMillisecond

class MillisecondSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Millisecond" should {

    "be created with a value between 0 and 999" in forAll { millisecond: Option[Millisecond] =>
      millisecond should !==(None)
    }

    "fail to be created with an Millisecond not between 0 and 999" in forAll(failedMillisecondGen) { failedMillisecond: Option[Millisecond] =>
      failedMillisecond should ===(None)
    }

    "creates a millisecond from a numeric millisecond" in forAll(millisecondAsIntGen) { millisecondAsInt: Int =>

      refineMillisecond(millisecondAsInt) match {
        case Right(m: NumericMillisecond) =>
          Millisecond(m) shouldBe a[Millisecond]
          Millisecond(m) should ===(Millisecond.fromInt(millisecondAsInt).get)
        case _ => fail
      }
    }
  }
}
