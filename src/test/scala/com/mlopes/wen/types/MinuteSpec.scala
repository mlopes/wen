package com.mlopes.wen.types

import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.prop.Checkers
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

    "be pattern matched" in {
      val minute = for {
        m <- Gen.choose(0, 59)
      } yield Minute(m)

      val prop = forAll(minute) { m =>

        val matchResult = m.get match {
          case Minute(h1) =>
            m.get.minute ===(h1)
          case _ => false
        }
        matchResult ===(true)
      }
      check(prop)
    }
  }
}
