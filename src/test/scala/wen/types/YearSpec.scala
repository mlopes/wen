package wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._
import wen.test.Generators._
import wen.refine.refineYear
import wen.types.NumericTypes.NumericYear

class YearSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Year" should {

    "be created with a positive number" in forAll { year: Option[Year] =>
      year should !==(None)
    }

    "be created with AD as default EPOCH" in forAll (optionYearWithDefaultEpochGen) { optionYearWithDefaultEpoch: Option[Year] =>
      optionYearWithDefaultEpoch.get.epoch should ===(AD)
    }

    "fail to be created with a negative number" in forAll(failedYearGen) { failedYear: Option[Year] =>
      failedYear should ===(None)
    }

    "fail to be created with a zero" in forAll { epoch: Epoch =>
      Year(0, epoch) should ===(None)
    }

    "be created from a numeric year" in forAll(yearAsIntGen) { yearAsInt: Int =>
      forAll { epoch: Epoch =>
        refineYear(yearAsInt) match {
          case Right(y: NumericYear) =>
            Year(y, epoch) shouldBe a[Year]
            Year(y, epoch) should ===(Year(yearAsInt, epoch).get)
          case _ => fail
        }
      }
    }

    "be created from a numeric year wit AD as default Epoch" in forAll(yearAsIntGen) { yearAsInt: Int =>
      refineYear(yearAsInt) match {
        case Right(y: NumericYear) =>
          Year(y) shouldBe a[Year]
          Year(y) should ===(Year(yearAsInt, AD).get)
        case _ => fail
      }
    }
  }
}
