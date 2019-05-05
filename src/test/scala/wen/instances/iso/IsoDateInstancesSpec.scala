package wen.instances.iso

import cats.implicits._
import wen.instances.iso.DateInstances._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import wen.datetime.Date
import wen.types.{AD, BC, Day, January, July, March, Year}
import eu.timepit.refined.auto._

class IsoDateInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "ISO DateInstances" should {

    "provide ISO show instance for Date" in {
      val date1 = Date.unsafe(Day(25), July, Year(1975, AD))
      val date2 = Date.unsafe(Day(1), January, Year(2131, BC))
      val date3 = Date.unsafe(Day(2), March, Year(1, BC))

      date1.show should ===("1975-07-25")
      date2.show should ===("-2130-01-01")
      date3.show should ===("0000-03-02")
    }
  }
}
