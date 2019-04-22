package com.mlopes.wen.instances

import cats.Eq
import cats.implicits._
import com.mlopes.wen.instances.MinuteInstances._
import com.mlopes.wen.types.Minute
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class MinuteInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Minute Instances" should {
    "provide order" in {
      val minute1 = Minute(24).get
      val minute2 = Minute(8).get
      val minute3 = Minute(59).get
      (minute1 compare minute1) should ===(0)
      (minute1 compare minute2) > 0 should ===(true)
      (minute2 compare minute3) < 0 should ===(true)
    }

    "provide eq" in {
      val minute1 = Minute(10).get
      val minute2 = Minute(36).get
      val minute3 = Minute(41).get

      Eq[Minute].eqv(minute1, minute1) should ===(true)
      Eq[Minute].neqv(minute1, minute2) should ===(true)
      Eq[Minute].neqv(minute2, minute3) should ===(true)
    }

    "provide show" in {
      val minute1 = Minute(8).get
      val minute2 = Minute(39).get
      val minute3 = Minute(4).get

      minute1.show should ===("08")
      minute2.show should ===("39")
      minute3.show should !==("4")
    }
  }
}
