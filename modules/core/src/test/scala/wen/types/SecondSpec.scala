package wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._
import wen.test.Generators._
import wen.refine.refineSecond
import wen.types.NumericTypes.NumericSecond

class SecondSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Second" should {

    "be created with a value between 0 and 59" in forAll { second: Option[Second] =>
      second should !==(None)
    }

    "fail to be created with an Second not between 0 and 59" in forAll(failedSecondGen) { failedSecond: Option[Second] =>
      failedSecond should ===(None)
    }

    "creates a second from a numeric second" in forAll(secondAsIntGen) { secondAsInt: Int =>
      refineSecond(secondAsInt) match {
        case Right(s: NumericSecond) =>
          Second(s) shouldBe a[Second]
          Second(s) should ===(Second.fromInt(secondAsInt).get)
        case _ => fail
      }
    }
  }
}
