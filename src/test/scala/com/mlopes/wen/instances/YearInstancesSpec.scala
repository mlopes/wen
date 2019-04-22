package com.mlopes.wen.instances

import cats.Eq
import cats.implicits._
import eu.timepit.refined.auto._
import org.scalatest.{Matchers, WordSpec}
import com.mlopes.wen.types._
import com.mlopes.wen.instances.YearInstances._
import com.mlopes.wen.types.NumericTypes.NumericYear
import org.scalactic.TypeCheckedTripleEquals

class YearInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  "Year Instances" should {
    "provide order" in {
      val year1: Year = Year(1999, BC)
      val year2: Year = Year(1999, AD)
      val year3: Year = Year(2019, AD)
      val year4: Year = Year(3019, BC)

      (year1 compare year1) should ===(0)
      (year1 compare year2) < 0 should ===(true)
      (year3 compare year1) > 0 should ===(true)
      (year3 compare year4) > 0 should ===(true)
    }

    "provide eq" in {
      val year1: Year = Year(1999, BC)
      val year2: Year = Year(1999, AD)
      val year3: Year = Year(2019, BC)

      Eq[Year].eqv(year1, year1) should ===(true)
      Eq[Year].neqv(year2, year1) should ===(true)
      Eq[Year].neqv(year3, year1) should ===(true)
    }

    "provide show" in {
      val numericYear: NumericYear = 2019
      Year(numericYear, AD).show should ===("AD 2019")
      Year(numericYear, BC).show should ===("2019 BC")
    }
  }
}
