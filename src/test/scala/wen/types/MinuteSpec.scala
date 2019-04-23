package wen.types

import eu.timepit.refined.{W, refineV}
import eu.timepit.refined.numeric.Interval
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.{Matchers, WordSpec}

class MinuteSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {

  "Minute" should {

    "be created with a value between 0 and 59" in {

      val minute = for {
        m <- Gen.choose(0, 59)
      } yield Minute(m)

      val prop = forAll(minute) { m =>
        m !==(None)
      }
      check(prop)
    }

    "fail to be created with an Minute not between 0 and 59" in {
      val minute = for {
        m <- Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 59)
      } yield Minute(m)

      val prop = forAll(minute) { m =>
        m ===(None)
      }
      check(prop)
    }

    "creates a minute from a numeric minute" in {
      val numericMinute = Gen.choose(0, 59)

      val prop = forAll[Int, Boolean](numericMinute) { m: Int =>
        refineV[Interval.Closed[W.`0`.T, W.`59`.T]](m)
          .fold(_ => false, { x =>
            val numericMinute = Minute(x)
            val optionMinute = Minute(m).get
            numericMinute ===(optionMinute)})
      }

      check(prop)
    }
  }
}
