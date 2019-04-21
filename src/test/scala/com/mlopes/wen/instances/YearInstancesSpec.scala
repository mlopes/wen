package com.mlopes.wen.instances

import cats._
import eu.timepit.refined.auto._
import org.scalatest.{Matchers, WordSpec}
import com.mlopes.wen.types._
import com.mlopes.wen.instances.YearInstances._
import org.scalactic.TypeCheckedTripleEquals

class YearInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  "Year Instances" should {
    "provide order" in {
      val year1 = Year.fromNumericYear(1999, BC)
      val year2 = Year.fromNumericYear(1999, AD)
      val year3 = Year.fromNumericYear(2019, AD)
      val year4 = Year.fromNumericYear(3019, BC)

      (Order[Year].compare(year1, year1) ===(0)) should ===(true)
      (Order[Year].compare(year1, year2) < 0) should ===(true)
      (Order[Year].compare(year3, year1) > 0) should ===(true)
      (Order[Year].compare(year3, year4) > 0) should ===(true)
    }

    "provide eq" in {
      val year1 = Year.fromNumericYear(1999, BC)
      val year2 = Year.fromNumericYear(1999, AD)
      val year3 = Year.fromNumericYear(2019, BC)

      Eq[Year].eqv(year1, year1) should ===(true)
      Eq[Year].eqv(year2, year1) should ===(false)
      Eq[Year].eqv(year3, year1) should ===(false)
    }

    "provide show" in {
      Show[Year].show(Year(2019, AD).get) should ===("AD 2019")
      Show[Year].show(Year(2019, BC).get) should ===("2019 BC")
    }
  }
}
