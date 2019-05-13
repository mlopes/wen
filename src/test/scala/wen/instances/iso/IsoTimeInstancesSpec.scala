package wen.instances.iso

import cats.implicits._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class IsoTimeInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  import wen.instances.datetime.TimeInstancesSpec._

  "ISO TimeInstances" should {

    "provide ISO format show for Time" in {
      time1.show should ===("08:53:23")
    }

    "provide ISO format show for ZoneTime" in {
      zoneTime7.show should ===("08:15:02Z")
      zoneTime8.show should ===("10:31:23-01:00")
      zoneTime9.show should ===("07:53:23+00:30")
    }

    "provide ISO format show for Offset" in {
      offset1.show should ===("Z")
      offset2.show should ===("Z")
      offset3.show should ===("Z")
      offset4.show should ===("+02:10")
      offset5.show should ===("-10:02")
    }
  }
}
