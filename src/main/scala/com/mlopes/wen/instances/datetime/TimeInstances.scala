package com.mlopes.wen.instances.datetime

import cats.implicits._
import cats.{Order, Show}
import com.mlopes.wen.datetime.Time
import com.mlopes.wen.instances.HourInstances._
import com.mlopes.wen.instances.MinuteInstances._
import com.mlopes.wen.instances.MillisecondInstances._
import com.mlopes.wen.instances.SecondInstances._

object TimeInstances {

  implicit val timeOrderInstance: Order[Time] = new Order[Time] {
    override def compare(x: Time, y: Time): Int = {
      lazy val timeToInt: Time => Int = { t =>
        val minutes = t.hour.hour.value * 60 + t.minute.minute.value
        val seconds = minutes * 60 + t.second.second.value
        seconds * 1000 + t.millisecond.millisecond.value
      }

      Order[Int].compare(timeToInt(x), timeToInt(y))
    }
  }

  implicit val timeShowInstance: Show[Time] = new Show[Time] {
    override def show(t: Time): String =
      s"${t.hour.show}:${t.minute.show}:${t.second.show}.${t.millisecond.show}"
  }
}
