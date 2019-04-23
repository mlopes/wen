package wen.types

import eu.timepit.refined.{W, refineV}
import eu.timepit.refined.numeric.Interval
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.{Matchers, WordSpec}

class DaySpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {

  "Day" should {

    "be created with a value between 1 and 31" in {

      val day = for {
        d <- Gen.choose(1, 31)
      } yield Day(d)

      val prop = forAll(day) { d =>
        d !==(None)
      }
      check(prop)
    }

    "fail to be created with an day not between 1 and 31" in {
      val day = for {
        d <- Arbitrary.arbitrary[Int] suchThat (x => x < 1 || x > 31)
      } yield Day(d)

      val prop = forAll(day) { d =>
        d ===(None)
      }
      check(prop)
    }

    "creates a day from a numeric day" in {
      val numericDay = Gen.choose(1, 31)

      val prop = forAll[Int, Boolean](numericDay) { d: Int =>
        refineV[Interval.Closed[W.`1`.T, W.`31`.T]](d)
          .fold(_ => false, {x =>
            val numericDay = Day(x)
            val optionDay = Day(d).get
            numericDay ===(optionDay)})
      }

      check(prop)
    }
  }
}
