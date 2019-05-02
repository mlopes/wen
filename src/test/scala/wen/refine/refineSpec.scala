package wen.refine

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.WordSpec
import org.scalacheck.Prop.forAll
import org.scalatestplus.scalacheck.Checkers

class refineSpec extends WordSpec with Checkers {

  "refineSecond" should {

    "refine Second" in {
      val second = Gen.choose(0, 59)

      val prop = forAll(second) { s: Int =>
        refineSecond(s) match {
          case Right(s1) => s1.value === (s)
          case _ => false
        }
      }

      check(prop)
    }

    "fail to refine non-second" in {
      val notASecond = Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 59)

      val prop = forAll(notASecond) { s =>
        refineSecond(s) match {
          case Left(_) => true
          case _ => false
        }
      }

      check(prop)
    }
  }

  "refineMinute" should {

    "refine Minute" in {
      val minute = Gen.choose(0, 59)

      val prop = forAll(minute) { m =>
        refineMinute(m) match {
          case Right(m1) => m1.value === (m)
          case _ => false
        }
      }

      check(prop)
    }

    "fail to refine non-minute" in {
      val notAMinute = Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 59)

      val prop = forAll(notAMinute) { m =>
        refineMinute(m) match {
          case Left(_) => true
          case _ => false
        }
      }

      check(prop)
    }
  }

  "refineYear" should {

    "refine Year" in {
      val year = Gen.posNum[Int]

      val prop = forAll(year) { y =>
        refineYear(y) match {
          case Right(y1) => y1.value === (y)
          case _ => false
        }
      }

      check(prop)
    }

    "fail to refine negative numbers" in {
      val notAYear = Gen.negNum[Int]

      val prop = forAll(notAYear) { y =>
        refineYear(y) match {
          case Left(_) => true
          case _ => false
        }
      }

      check(prop)
    }

    "should fail to refine 0" in {
      val prop = refineYear(0) match {
        case Left(_) => true
        case _ => false
      }

      check(prop)
    }
  }

  "refineHour" should {

    "refine Hour" in {
      val hour = Gen.choose(1, 23)

      val prop = forAll(hour) { h =>
        refineHour(h) match {
          case Right(h1) => h1.value === (h)
          case _ => false
        }
      }

      check(prop)
    }

    "fail to refine non-hour" in {
      val notAHour = Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 23)

      val prop = forAll(notAHour) { h =>
        refineHour(h) match {
          case Left(_) => true
          case _ => false
        }
      }

      check(prop)
    }
  }

  "refineMillisecond" should {

    "refine Millisecond" in {
      val millisecond = Gen.choose(0, 999)

      val prop = forAll(millisecond) { m =>
        refineMilliSecond(m) match {
          case Right(m1) => m1.value === (m)
          case _ => false
        }
      }

      check(prop)
    }

    "fail to refine non-millisecond" in {
      val notAMillisecond = Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 999)

      val prop = forAll(notAMillisecond) { m =>
        refineMilliSecond(m) match {
          case Left(_) => true
          case _ => false
        }
      }

      check(prop)
    }
  }

  "refineDay" should {

    "refine Day" in {
      val day = Gen.choose(1, 31)

      val prop = forAll(day) { d =>
        refineDay(d) match {
          case Right(d1) => d1.value === (d)
          case _ => false
        }
      }

      check(prop)
    }

    "fail to refine non-day" in {
      val notADay = Arbitrary.arbitrary[Int] suchThat (x => x < 1 || x > 31)

      val prop = forAll(notADay) { d =>
        refineMonth(d) match {
          case Left(_) => true
          case _ => false
        }
      }

      check(prop)
    }
  }

  "refineMonth" should {
    "refine Month" in {
      val month = Gen.choose(1, 12)

      val prop = forAll(month) { m =>
        refineMonth(m) match {
          case Right(m1) => m1.value ===(m)
          case _ => false
        }
      }

      check(prop)
    }

    "fail to refine non-month" in {
      val notAMonth = Arbitrary.arbitrary[Int] suchThat (x => x < 1 || x > 12)

      val prop = forAll(notAMonth) { m =>
        refineMonth(m) match {
          case Left(_) => true
          case _ => false
        }
      }

      check(prop)
    }
  }
}
