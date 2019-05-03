package wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}
import wen.test.Arbitraries._
import wen.test.Generators._
import wen.types.NumericTypes.NumericDay
import wen.refine.refineDay

class DaySpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Day" should {

    "be created with a value between 1 and 31" in forAll { day: Option[Day] =>
      day should !==(None)
    }

    "fail to be created with an day not between 1 and 31" in forAll (failedDayGen) { failedDay: Option[Day] =>
      failedDay should ===(None)
    }

    "creates a day from a numeric day" in forAll(monthDayAsIntGen) { monthDayAsInt: Int =>

      refineDay(monthDayAsInt) match {
        case Right(day: NumericDay) =>
          Day(day) shouldBe a[Day]
          Day(day) should ===(Day(monthDayAsInt).get)
        case _ => fail
      }
    }
  }
}
