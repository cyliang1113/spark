package cn.scalademo.functionprogram

object Demo {
  def main(args: Array[String]): Unit = {
    sum1(0)(1, 2)
    sum2(1)
  }

  def sum1(i: Int) = {
    println(i)
    (a: Int, b: Int) => println("a: " + a + ", b: " + b)
  }

  var sum2 = sum1(_)

}