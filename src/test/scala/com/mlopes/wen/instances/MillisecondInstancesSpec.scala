package com.mlopes.wen.instances

import cats._
import com.mlopes.wen.instances.MillisecondInstances._
import com.mlopes.wen.types.Millisecond
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class MillisecondInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Millisecond Instances" should {
    "provide order" in {
      val millisecond1 = Millisecond(257).get
      val millisecond2 = Millisecond(36).get
      val millisecond3 = Millisecond(481).get
      (Order[Millisecond].compare(millisecond1, millisecond1) == 0) should ===(true)
      (Order[Millisecond].compare(millisecond1, millisecond2) > 0) should ===(true)
      (Order[Millisecond].compare(millisecond2, millisecond3) < 0) should ===(true)
    }

    "provide eq" in {
      val millisecond1 = Millisecond(8).get
      val millisecond2 = Millisecond(143).get
      val millisecond3 = Millisecond(540).get

      Eq[Millisecond].eqv(millisecond1, millisecond1) should ===(true)
      Eq[Millisecond].eqv(millisecond1, millisecond2) should ===(false)
      Eq[Millisecond].eqv(millisecond2, millisecond3) should ===(false)
    }

    "provide show" in {
      val millisecond1 = Millisecond(2).get
      val millisecond2 = Millisecond(999).get

      Show[Millisecond].show(millisecond1) should ===("2")
      Show[Millisecond].show(millisecond2) should ===("999")
    }
  }

}
