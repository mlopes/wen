package com.mlopes.wen.types

import eu.timepit.refined.{W, refineV}
import eu.timepit.refined.numeric.Interval
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.{Matchers, WordSpec}

class MillisecondSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {

  "Millisecond" should {

    "be created with a value between 0 and 999" in {

      val millisecond = for {
        m <- Gen.choose(0, 999)
      } yield Millisecond(m)

      val prop = forAll(millisecond) { m =>
        m !==(None)
      }
      check(prop)
    }

    "fail to be created with an Millisecond not between 0 and 999" in {
      val millisecond = for {
        m <- Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 999)
      } yield Millisecond(m)

      val prop = forAll(millisecond) { m =>
        m ===(None)
      }
      check(prop)
    }

    "be pattern matched" in {
      val millisecond = for {
        m <- Gen.choose(0, 999)
      } yield Millisecond(m)

      val prop = forAll(millisecond) { m =>

        val matchResult = m.get match {
          case Millisecond(m1) =>
            m.get.millisecond ===(m1)
          case _ => false
        }
        matchResult ===(true)
      }
      check(prop)
    }

    "creates a millisecond from a numeric millisecond" in {
      val numericMillisecond = Gen.choose(0, 999)

      val prop = forAll[Int, Boolean](numericMillisecond) { m: Int =>
        refineV[Interval.Closed[W.`0`.T, W.`999`.T]](m)
          .fold(_ => false, {x =>
            val numericMillisecond = Millisecond(x)
            val optionMillisecond = Millisecond(m).get
            numericMillisecond ===(optionMillisecond)})
      }

      check(prop)
    }
  }
}
