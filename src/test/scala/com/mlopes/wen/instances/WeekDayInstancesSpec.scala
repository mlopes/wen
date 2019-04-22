package com.mlopes.wen.instances

import cats._
import cats.implicits._
import com.mlopes.wen.instances.WeekDayInstances._
import com.mlopes.wen.types._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class WeekDayInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "WeekDay Instances" should {

    "provide order" in {
      ((Sunday: WeekDay) compare Monday) < 0 should  ===(true)
      ((Wednesday: WeekDay) compare Monday) > 0 should  ===(true)
      ((Tuesday: WeekDay) compare Tuesday) should  ===(0)
    }

    "provide eq" in {
      Eq[WeekDay].eqv(Friday, Friday) should ===(true)
      Eq[WeekDay].neqv(Saturday, Friday) should ===(true)
    }

    "provide show" in {
      (Sunday: WeekDay).show should ===("Sunday")
      (Monday: WeekDay).show should ===("Monday")
      (Tuesday: WeekDay).show should ===("Tuesday")
      (Wednesday: WeekDay).show should ===("Wednesday")
      (Thursday: WeekDay).show should ===("Thursday")
      (Friday: WeekDay).show should ===("Friday")
      (Saturday: WeekDay).show should ===("Saturday")
    }

  }
}
