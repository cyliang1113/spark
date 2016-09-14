package cn.scalademo.implicittrans

class Man(var name: String)
object Man {
  implicit def man2SuperMan(man: Man) = new SuperMan(man.name)
}

class SuperMan(var name: String) {
  def trans() {
    println(this.name + " hahaha.")
  }
}
