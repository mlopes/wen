package com.mlopes.wen.types

import eu.timepit.refined.{W, refineV}
import eu.timepit.refined.numeric.Interval
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.{Matchers, WordSpec}

class HourSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {

  "Hour" should {

    "be created with a value between 0 and 23" in {

      val hour = for {
        h <- Gen.choose(0, 23)
      } yield Hour(h)

      val prop = forAll(hour) { h =>
        h !==(None)
      }
      check(prop)
    }

    "fail to be created with an hour not between 0 and 23" in {
      val hour = for {
        h <- Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 23)
      } yield Hour(h)

      val prop = forAll(hour) { h =>
        h ===(None)
      }
      check(prop)
    }

    "creates a hour from a numeric hour" in {
      val numericHour = Gen.choose(0, 23)

      val prop = forAll[Int, Boolean](numericHour) { h: Int =>
        refineV[Interval.Closed[W.`0`.T, W.`23`.T]](h)
          .fold(_ => false, {x =>
            val numericHour = Hour(x)
            val optionHour = Hour(h).get
            numericHour ===(optionHour)})
      }

      check(prop)
    }
  }
}
