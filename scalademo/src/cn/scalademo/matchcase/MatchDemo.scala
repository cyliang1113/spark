package cn.scalademo.matchcase

class Person
case class Student(name: String, age: Int) extends Person
case class Teacher(name: String, age: Int) extends Person

object Demo {
  def main(args: Array[String]): Unit = {
    var value = "A"
    value match {
      case "A" => println(1)
      case "B" => println(2)
    }

    //    var v1 = getSalary("spark")
    //    println(v1);
    //println(getSalary("c"));

    getKind(1)

    //getType(Student("lili", 23))

  }

  def getSalary(name: String) = {
    name match {
      case "hadoop"                      => "$150k/y"
      case "spark"                       => "$200k/y"
      case _ if name == "c"              => "110k/y"
      case name1 if "java".equals(name1) => "100k/y"
      case _                             => "$90k/y"
    }
  }

  def getKind(v: Any) = {
    v match {
      case i: Int    => println("Int: " + i)
      case i: Double => println("Double: " + i)
      case _         => println("else")
    }
  }

  def getType(p: Person) = {
    p match {
      case Student(name, age) => println("Student name: " + name + " age: " + age)
      case Teacher(name, age) => println("Teacher name: " + name + " age: " + age)
      case _                  => println("else")
    }
  }
}