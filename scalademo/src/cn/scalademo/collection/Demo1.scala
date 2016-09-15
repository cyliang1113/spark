package cn.scalademo.collection

import sun.org.mozilla.javascript.internal.ast.Yield

object Demo1 {
  def main(args: Array[String]): Unit = {
    val arr = new Array[Int](5);
    arr(0) = 1
    arr(4) = 5
    for (i <- arr) {
      println(i)
    }
    var arr2 = for (i <- arr) yield {i + 1}
    println(arr2.mkString)
    
    var arr3 = arr2.filter { x => x > 0 }
    println(arr3.isInstanceOf[String])
  }
}
