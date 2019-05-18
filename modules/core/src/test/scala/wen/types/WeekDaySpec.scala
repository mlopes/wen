package wen.types

import cats.implicits._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class WeekDaySpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "WeekDay" should {

    "be created from String" in {
      WeekDay.fromString("Sunday") should ===(Sunday.some)
      WeekDay.fromString("Monday") should ===(Monday.some)
      WeekDay.fromString("Tuesday") should ===(Tuesday.some)
      WeekDay.fromString("Wednesday") should ===(Wednesday.some)
      WeekDay.fromString("Thursday") should ===(Thursday.some)
      WeekDay.fromString("Friday") should ===(Friday.some)
      WeekDay.fromString("Saturday") should ===(Saturday.some)
    }
  }
}
