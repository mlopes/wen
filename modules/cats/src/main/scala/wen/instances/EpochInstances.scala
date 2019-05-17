package wen.instances

import cats._
import wen.types.{AD, BC, Epoch}

object EpochInstances extends EpochInstances

trait EpochInstances {

  implicit val epochOrderInstance: Order[Epoch] = new Order[Epoch] {
    override def compare(x: Epoch, y: Epoch): Int =
      (x, y) match {
        case (e1, e2) if e1 == e2 => 0
        case (AD, _) => 1
        case (BC, _) => -1
      }
  }

  implicit val epochShowInstance: Show[Epoch] = new Show[Epoch] {
    override def show(t: Epoch): String = t match {
      case AD => "AD"
      case BC => "BC"
    }
  }
}
