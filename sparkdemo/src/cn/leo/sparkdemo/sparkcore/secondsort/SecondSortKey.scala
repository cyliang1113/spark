package cn.leo.sparkdemo.sparkcore.secondsort

import scala.math.Ordered

class SecondSortKey(val first: Int, val second: Int) extends Ordered[SecondSortKey] with Serializable {

  def compare(other: SecondSortKey): Int = {
    if (this.first - other.first != 0) {
      this.first - other.first
    } else if (this.second - other.second != 0) {
      this.second - other.second
    } else {
      0
    }
  }

}