package com.mlopes.wen.instances

import cats._
import org.scalatest.{Matchers, WordSpec}
import com.mlopes.wen.types.{August, December, February, January, July, March, May, Month, September}
import com.mlopes.wen.instances.MonthInstances.monthOrderInstance
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
      Eq[Month].eqv(September, March) should ===(false)
    }
  }
}
