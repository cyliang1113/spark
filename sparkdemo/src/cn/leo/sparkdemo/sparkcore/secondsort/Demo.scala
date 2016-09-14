package cn.leo.sparkdemo.sparkcore.secondsort

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Demo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("second sort").setMaster("local");

    val sc = new SparkContext(conf)

    sc.textFile("D:/secondsort.txt").map {
      x =>
        {
          val s = x.split(" ")
          (new SecondSortKey(s(0).toInt, s(1).toInt), x)
        }
    }.sortByKey().map(_._2).collect().foreach { println }

  }
}