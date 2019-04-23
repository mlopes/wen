package wen.types

import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.refineV
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.{Matchers, WordSpec}

class YearSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {

  "Year" should {

    "be created with a positive number" in {
      val year = for {
        y <- Gen.posNum[Int]
        e <- Gen.oneOf(BC, AD)
      } yield Year(y, e)

      val prop = forAll(year) { y =>
        y !==(None)
      }
      check(prop)
    }

    "fail to be created with a negative number" in {
      val year = for {
        y <- Gen.negNum[Int]
        e <- Gen.oneOf(BC, AD)
      } yield Year(y, e)

      val prop = forAll(year) { y =>
        y ===(None)
      }
      check(prop)
    }

    "fail to be created with a zero" in {
      val year = for {
        e <- Gen.oneOf(BC, AD)
      } yield Year(0, e)

      val prop = forAll(year) { y =>
        y ===(None)
      }
      check(prop)
    }

    "creates a year from a numeric year" in {
      case class TestYear(y: Int, e: Epoch)

      val testValues: Gen[TestYear] = for {
        ny <- Gen.posNum[Int]
        epoch <- Gen.oneOf(BC, AD)
      } yield TestYear(ny, epoch)

      val prop = forAll[TestYear, Boolean](testValues) { y: TestYear =>
        refineV[Positive](y.y)
          .fold(_ => false, { x =>
            val yearFromNumberic = Year(x, y.e)
            val yearFromOption = Year(y.y, y.e).get
          yearFromNumberic ===(yearFromOption)})
      }

      check(prop)
    }
  }
}
