package com.mlopes.wen.instances

import cats._
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
      (Order[Minute].compare(minute1, minute1) == 0) should ===(true)
      (Order[Minute].compare(minute1, minute2) > 0) should ===(true)
      (Order[Minute].compare(minute2, minute3) < 0) should ===(true)
    }

    "provide eq" in {
      val minute1 = Minute(10).get
      val minute2 = Minute(36).get
      val minute3 = Minute(41).get

      Eq[Minute].eqv(minute1, minute1) should ===(true)
      Eq[Minute].eqv(minute1, minute2) should ===(false)
      Eq[Minute].eqv(minute2, minute3) should ===(false)
    }

    "provide show" in {
      val minute1 = Minute(8).get
      val minute2 = Minute(39).get
      val minute3 = Minute(4).get

      Show[Minute].show(minute1) should ===("08")
      Show[Minute].show(minute2) should ===("39")
      Show[Minute].show(minute3) should !==("4")
    }
  }

}
