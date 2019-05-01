package wen.datetime

import eu.timepit.refined.auto._
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.Checkers
import wen.types._

class TimeSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Checkers {
  "Time" should {
    "be created from hour with everything else defaulting to 0" in {
      val time = for {
        h <- Gen.choose(0, 23)
      } yield Time(Hour(h).get)

      val prop = forAll(time) { t =>
        val hour: Option[Hour] = Hour(t.hour.hour.value)
        t ===(Time(hour.get, Minute(0), Second(0), Millisecond(0)))
      }
      check(prop)
    }

    "be created from hour and minute with everything else defaulting to 0" in {
      val time = for {
        h <- Gen.choose(0, 23)
        m <- Gen.choose(0, 59)
      } yield Time(Hour(h).get, Minute(m).get)

      val prop = forAll(time) { t =>
        val hour: Option[Hour] = Hour(t.hour.hour.value)
        val minute: Option[Minute] = Minute(t.minute.minute.value)
        t ===(Time(hour.get, minute.get, Second(0), Millisecond(0)))
      }
      check(prop)
    }

    "be created from hour, minute and second with milliseocnd defaulting to 0" in {
      val time = for {
        h <- Gen.choose(0, 23)
        m <- Gen.choose(0, 59)
        s <- Gen.choose(0, 59)
      } yield Time(Hour(h).get, Minute(m).get, Second(s).get)

      val prop = forAll(time) { t =>
        val hour: Option[Hour] = Hour(t.hour.hour.value)
        val minute: Option[Minute] = Minute(t.minute.minute.value)
        val second: Option[Second] = Second(t.second.second.value)
        t ===(Time(hour.get, minute.get, second.get, Millisecond(0)))
      }
      check(prop)
    }
  }
}