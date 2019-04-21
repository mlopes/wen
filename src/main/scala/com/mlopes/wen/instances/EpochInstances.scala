package com.mlopes.wen.instances

import cats.Order
import com.mlopes.wen.types.{AD, BC, Epoch}

object EpochInstances {

  implicit val epochOrderInstance: Order[Epoch] = new Order[Epoch] {
    override def compare(x: Epoch, y: Epoch): Int =
      (x, y) match {
        case (e1, e2) if e1 == e2 => 0
        case (AD, _) => 1
        case (BC, _) => -1
      }
  }
}
