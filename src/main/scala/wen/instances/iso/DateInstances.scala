package wen.instances.iso

import cats.implicits._
import cats.Show
import wen.datetime.Date
import wen.types.{Month, _}

object DateInstances extends DateInstances

trait DateInstances {
  implicit val isoDateShowInstance: Show[Date] = new Show[Date] {
    override def show(t: Date): String =
      t match {
        case Date(Day(d), m: Month, Year(y, AD)) if (y.value.toString.length > 4) =>
          f"+${y.value}-${m.asInt}%02d-${d.value}%02d"
        case Date(Day(d), m: Month, Year(y, AD)) =>
          f"${y.value}%04d-${m.asInt}%02d-${d.value}%02d"
        case Date(Day(d), m: Month, Year(y, BC)) if ((y.value * -1) + 1) === 0 =>
          val year: Int = (y.value * -1) + 1
          f"${year}%04d-${m.asInt}%02d-${d.value}%02d"
        case Date(Day(d), m: Month, Year(y, BC)) =>
          val year: Int = (y.value * -1) + 1
          f"${year}%05d-${m.asInt}%02d-${d.value}%02d"
      }
  }

}
