package com.mlopes.wen.types

import eu.timepit.refined._
import eu.timepit.refined.numeric.Interval
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.{Matchers, WordSpec}

class SecondSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {

  "Second" should {

    "be created with a value between 0 and 59" in {

      val second = for {
        s <- Gen.choose(0, 59)
      } yield Second(s)

      val prop = forAll(second) { s =>
        s !==(None)
      }
      check(prop)
    }

    "fail to be created with an Second not between 0 and 59" in {
      val second = for {
        s <- Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 59)
      } yield Second(s)

      val prop = forAll(second) { s =>
        s ===(None)
      }
      check(prop)
    }

    "be pattern matched" in {
      val second = for {
        s <- Gen.choose(0, 59)
      } yield Second(s)

      val prop = forAll(second) { s =>

        val matchResult = s.get match {
          case Second(s1) =>
            s.get.second ===(s1)
          case _ => false
        }
        matchResult ===(true)
      }
      check(prop)
    }

    "creates a second from a numeric second" in {
      val numericSecond = Gen.choose(0, 59)

      val prop = forAll[Int, Boolean](numericSecond) { s: Int =>
        refineV[Interval.Closed[W.`0`.T, W.`59`.T]](s)
          .fold(_ => false, {x =>
            val numericSecond = Second.fromNumericSecond(x)
            val optionSecond = Second(s).get
            numericSecond.second.value ===(optionSecond.second.value)})
      }

      check(prop)
    }
  }
}
