package com.mlopes.wen.types

import eu.timepit.refined._
import eu.timepit.refined.numeric.Interval
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.{Matchers, WordSpec}

class MonthSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {

  "Month" should {

    "create a value for January from a number" in {
      Month(1) should ===(Some(January))
    }

    "create a value for February from a number" in {
      Month(2) should ===(Some(February))
    }

    "create a value for March from a number" in {
      Month(3) should ===(Some(March))
    }
    "create a value for April from a number" in {
      Month(4) should ===(Some(April))
    }
    "create a value for May from a number" in {
      Month(5) should ===(Some(May))
    }
    "create a value for June from a number" in {
      Month(6) should ===(Some(June))
    }
    "create a value for July from a number" in {
      Month(7) should ===(Some(July))
    }
    "create a value for August from a number" in {
      Month(8) should ===(Some(August))
    }
    "create a value for September from a number" in {
      Month(9) should ===(Some(September))
    }
    "create a value for October from a number" in {
      Month(10) should ===(Some(October))
    }
    "create a value for November from a number" in {
      Month(11) should ===(Some(November))
    }
    "create a value for December from a number" in {
      Month(12) should ===(Some(December))
    }
    "fail to create a value from a number that doesn't correspond to a month" in {
      Month(13) should ===(None)
    }

    "return a 1 for January" in {
      Month.toInt(January) should ===(1)
    }
    "return a 2 for February" in {
      Month.toInt(February) should ===(2)
    }
    "return a 3 for March" in {
      Month.toInt(March) should ===(3)
    }
    "return a 4 for April" in {
      Month.toInt(April) should ===(4)
    }
    "return a 5 for May" in {
      Month.toInt(May) should ===(5)
    }
    "return a 6 for June" in {
      Month.toInt(June) should ===(6)
    }
    "return a 7 for July" in {
      Month.toInt(July) should ===(7)
    }
    "return a 8 for August" in {
      Month.toInt(August) should ===(8)
    }
    "return a 9 for September" in {
      Month.toInt(September) should ===(9)
    }
    "return a 10 for October" in {
      Month.toInt(October) should ===(10)
    }
    "return a 11 for November" in {
      Month.toInt(November) should ===(11)
    }
    "return a 12 for December" in {
      Month.toInt(December) should ===(12)
    }

    "creates a month from a numeric month" in {
      val numericMonth = Gen.choose(1, 12)

      val prop = forAll[Int, Boolean](numericMonth) { m: Int =>
        refineV[Interval.Closed[W.`1`.T, W.`12`.T]](m)
          .fold(_ => false, Month.fromNumericMonth(_) ===(Month(m).get))
      }

      check(prop)
    }
  }
}
