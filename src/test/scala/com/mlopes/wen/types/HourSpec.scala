package com.mlopes.wen.types

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

    "be pattern matched" in {
      val hour = for {
        h <- Gen.choose(0, 23)
      } yield Hour(h)

      val prop = forAll(hour) { h =>

        val matchResult = h.get match {
          case Hour(h1) =>
            h.get.hour ===(h1)
          case _ => false
        }
        matchResult ===(true)
      }
      check(prop)
    }
  }
}
