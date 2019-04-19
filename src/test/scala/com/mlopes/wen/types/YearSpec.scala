package com.mlopes.wen.types

import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.prop.Checkers
import org.scalatest.{Matchers, WordSpec}

class YearSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {

  "Year" should {

    "be created with a positive number" in {
      val year = for {
        y <- Gen.posNum[Int]
        e <- Gen.oneOf(BC, AC)
      } yield Year(y, e)

      val prop = forAll(year) { y =>
        y !==(None)
      }
      check(prop)
    }

    "fail to be created with a negative number" in {
      val year = for {
        y <- Gen.negNum[Int]
        e <- Gen.oneOf(BC, AC)
      } yield Year(y, e)

      val prop = forAll(year) { y =>
        y ===(None)
      }
      check(prop)
    }

    "fail to be created with a zero" in {
      val year = for {
        e <- Gen.oneOf(BC, AC)
      } yield Year(0, e)

      val prop = forAll(year) { y =>
        y ===(None)
      }
      check(prop)
    }

    "be pattern matched" in {
      val year = for {
        y <- Gen.posNum[Int]
        e <- Gen.oneOf(BC, AC)
      } yield Year(y, e)

      val prop = forAll(year) { y =>

        val matchResult = y.get match {
          case Year(y1, e) =>
            y.get.year ===(y1) && y.get.epoch ===(e)
          case _ => false
        }
        matchResult ===(true)
      }
      check(prop)
    }
  }
}
