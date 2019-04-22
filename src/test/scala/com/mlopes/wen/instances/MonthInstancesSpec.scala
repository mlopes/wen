package com.mlopes.wen.instances

import cats._
import org.scalatest.{Matchers, WordSpec}
import com.mlopes.wen.types._
import com.mlopes.wen.instances.MonthInstances._
import org.scalactic.TypeCheckedTripleEquals

class MonthInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  "Month Instances" should {
    "provide order" in {
      (Order[Month].compare(January, February) < 0) should ===(true)
      (Order[Month].compare(August, May) > 0) should ===(true)
      Order[Month].compare(July, July) should ===(0)
    }

    "provide eq" in {
      Eq[Month].eqv(December, December) should ===(true)
      Eq[Month].neqv(September, March) should ===(true)
    }

    "provide show" in {
      Show[Month].show(January) should ===("January")
      Show[Month].show(February) should ===("February")
      Show[Month].show(March) should ===("March")
      Show[Month].show(April) should ===("April")
      Show[Month].show(May) should ===("May")
      Show[Month].show(June) should ===("June")
      Show[Month].show(July) should ===("July")
      Show[Month].show(August) should ===("August")
      Show[Month].show(September) should ===("September")
      Show[Month].show(October) should ===("October")
      Show[Month].show(November) should ===("November")
      Show[Month].show(December) should ===("December")
    }
  }
}
