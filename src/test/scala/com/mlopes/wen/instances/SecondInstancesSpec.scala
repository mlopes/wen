package com.mlopes.wen.instances

import cats._
import com.mlopes.wen.instances.SecondInstances._
import com.mlopes.wen.types.Second
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class SecondInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Second Instances" should {
    "provide order" in {
      val second1 = Second(27).get
      val second2 = Second(3).get
      val second3 = Second(48).get
      (Order[Second].compare(second1, second1) == 0) should ===(true)
      (Order[Second].compare(second1, second2) > 0) should ===(true)
      (Order[Second].compare(second2, second3) < 0) should ===(true)
    }

    "provide eq" in {
      val second1 = Second(8).get
      val second2 = Second(43).get
      val second3 = Second(54).get

      Eq[Second].eqv(second1, second1) should ===(true)
      Eq[Second].neqv(second1, second2) should ===(true)
      Eq[Second].neqv(second2, second3) should ===(true)
    }

    "provide show" in {
      val second1 = Second(3).get
      val second2 = Second(40).get
      val second3 = Second(1).get

      Show[Second].show(second1) should ===("03")
      Show[Second].show(second2) should ===("40")
      Show[Second].show(second3) should !==("1")
    }
  }

}
