package cn.leo.sparkdemo.sparkcore.wordcount

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object WordCount {

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      println("Usage : java -jar code.jar file_location save_location")
      System.exit(0)
    }

    val conf = new SparkConf().setAppName("first word count")
    conf.setMaster("local"); // 本地运行, 不需要spark
    
    val sc = new SparkContext(conf)
    
    val data = sc.textFile(args(0))

    data.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).saveAsTextFile(args(1))
    
    //data.filter(_.split(' ').length == 3).map(_.split(' ')(1)).map((_, 1)).reduceByKey(_ + _).map(x => (x._2, x._1)).sortByKey(false).map(x => (x._2, x._1)).saveAsTextFile(args(2))
 
    sc.stop()
  }
}