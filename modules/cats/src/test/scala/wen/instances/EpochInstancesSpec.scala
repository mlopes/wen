package wen.instances

import cats.Eq
import cats.implicits._
import wen.types.{AD, BC, Epoch}
import wen.instances.EpochInstances._
import org.scalatest.{Matchers, WordSpec}

class EpochInstancesSpec extends WordSpec with Matchers {

  "Epoch Instances" should {

    "provide order" in {
      val ad: Epoch = AD
      val bc: Epoch = BC
      (ad compare bc) > 0 should ===(true)
      (bc compare ad) < 0 should ===(true)
      (ad compare ad) should ===(0)
      (bc compare bc) should ===(0)
    }

    "provide eq" in {
      Eq[Epoch].eqv(AD, AD) should ===(true)
      Eq[Epoch].eqv(BC, BC) should ===(true)
      Eq[Epoch].neqv(AD, BC) should ===(true)
    }

    "provide show" in {
      val ad: Epoch = AD
      val bc: Epoch = BC

      ad.show should ===("AD")
      bc.show should ===("BC")
    }
  }
}
