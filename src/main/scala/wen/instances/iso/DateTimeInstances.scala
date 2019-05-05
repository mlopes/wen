package wen.instances.iso

import cats.implicits._
import cats.Show
import wen.datetime.{DateTime, ZoneDateTime}

object DateTimeInstances extends DateInstances

trait DateTimeInstances {
  implicit val isoDateTimeShowInstance: Show[DateTime] = new Show[DateTime] {
    override def show(t: DateTime): String = s"${t.date.show}T${t.time.show}"
  }

  implicit val isoZoneDateTimeShowInstance: Show[ZoneDateTime] = new Show[ZoneDateTime] {
    override def show(t: ZoneDateTime): String = s"${t.date.show}T${t.zoneTime.show}"
  }
}
