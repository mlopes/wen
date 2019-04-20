package com.mlopes.wen.instances

import cats.Order
import org.scalatest.{Matchers, WordSpec}
import com.mlopes.wen.types.{August, February, January, July, May, Month}
import com.mlopes.wen.instances.MonthInstances.monthOrderInstance
import org.scalactic.TypeCheckedTripleEquals

class MonthInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  "Month Instances" should {
    "provide order" in {
      (Order[Month].compare(January, February) < 0) should ===(true)
      (Order[Month].compare(August, May) > 0) should ===(true)
      Order[Month].compare(July, July) should ===(0)
    }
  }
}
