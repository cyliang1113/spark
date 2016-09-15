package cn.scalademo.base

object ControlStruct {
  def main(args: Array[String]): Unit = {
    for (i <- "hello spark".split(" ")) println(i)

    hello(age = 5, name = "leo")
  }

  def hello(name: String, age: Int) = {
    println("hello " + name + ", age " + age)
  }
}