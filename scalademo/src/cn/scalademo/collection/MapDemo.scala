package cn.scalademo.collection

import scala.collection.mutable.HashMap

object MapDemo {
  def main(args: Array[String]): Unit = {
    var map1 = HashMap[String, Int]("String" -> 1, "Int" -> 2)
    var opt1 = map1.get("String")
    println(opt1.getClass)
    var opt2 = map1.get("Double")
    println(opt2.getClass)
    println(map1.getOrElse("Double", 0))
  }
}