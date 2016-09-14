package cn.scalademo.implicittrans

import cn.scalademo.implicittrans.Man.man2SuperMan

object ImplicitTransDemo {

  def main(args: Array[String]): Unit = {
    var m = new Man("leo");
    m.trans();
  }
}