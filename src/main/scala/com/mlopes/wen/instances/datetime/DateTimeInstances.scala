package com.mlopes.wen.instances.datetime

import cats.{Order, Show}
import cats.implicits._
import com.mlopes.wen.datetime.DateTime
import com.mlopes.wen.instances.datetime.DateInstances._
import com.mlopes.wen.instances.datetime.TimeInstances._

object DateTimeInstances {

  implicit var dateTimeOrderInstance: Order[DateTime] = new Order[DateTime] {
    override def compare(x: DateTime, y: DateTime): Int = {
      if (x.date === y.date) {
        x.time compare y.time
      } else {
        x.date compare y.date
      }
    }
  }

  implicit var dateTimeShowInstance: Show[DateTime] = new Show[DateTime] {
    override def show(t: DateTime): String = s"${t.date.show} ${t.time.show}"
  }
}
