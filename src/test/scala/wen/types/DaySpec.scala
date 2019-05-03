package wen.types

import eu.timepit.refined.{W, refineV}
import eu.timepit.refined.numeric.Interval
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}
import wen.test.Arbitraries._
import wen.test.Generators._
import wen.types.NumericTypes.NumericDay

class DaySpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Day" should {

    "be created with a value between 1 and 31" in forAll { day: Option[Day] =>
      day should !==(None)
    }

    "fail to be created with an day not between 1 and 31" in forAll (failedDayGen) { notADay: Option[Day] =>
      notADay should ===(None)
    }

    "creates a day from a numeric day" in forAll(monthDayAsIntGen) { monthDayAsInt: Int =>

      refineV[Interval.Closed[W.`1`.T, W.`31`.T]](monthDayAsInt) match {
        case Right(day: NumericDay) =>
          Day(day) shouldBe a[Day]
          Day(day) should ===(Day(monthDayAsInt).get)
        case _ => fail
      }
    }
  }
}
