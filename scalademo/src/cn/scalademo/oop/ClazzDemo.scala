package cn.scalademo.oop

object ClazzDemo {
  def main(args: Array[String]): Unit = {
    var p = new Person("lili")
    p.address = "us"
    
    println(p.address)
  }
}