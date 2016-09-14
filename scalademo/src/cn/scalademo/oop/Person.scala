package cn.scalademo.oop

class Person(name: String) {
  var address: String = "cn"
  
  var nameN: String = name
  
  println("class Person")
  
  def this() = {
    this("lili")
  }
  
  def this(name: String, address: String) = {
    this(name)
    this.address = address;
  }
  
}