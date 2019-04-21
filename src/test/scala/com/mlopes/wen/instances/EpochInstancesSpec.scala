package com.mlopes.wen.instances

import cats._
import com.mlopes.wen.types.{AD, BC, Epoch}
import com.mlopes.wen.instances.EpochInstances._
import org.scalatest.{Matchers, WordSpec}

class EpochInstancesSpec extends WordSpec with Matchers {

  "Epoch Instances" should {

    "provide order" in {
      (Order[Epoch].compare(AD, BC) > 0) should ===(true)
      (Order[Epoch].compare(BC, AD) < 0) should ===(true)
      (Order[Epoch].compare(AD, AD) ===(0)) should ===(true)
      (Order[Epoch].compare(BC, BC) ===(0)) should ===(true)
    }

    "provide eq" in {
      Eq[Epoch].eqv(AD, BC) should ===(false)
      Eq[Epoch].eqv(AD, AD) should ===(true)
      Eq[Epoch].eqv(BC, BC) should ===(true)
    }

    "provide show" in {
      Show[Epoch].show(AD) should ===("AD")
      Show[Epoch].show(BC) should ===("BC")
    }
  }
}
