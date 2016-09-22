package cn.leo.sparkdemo.streaming

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds

object EmptyRddDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("EmptyRddDemo")
    conf.setMaster("local[3]")

    val streamingContext = new StreamingContext(conf, Seconds(10))

    // 客户端socket
    val lines = streamingContext.socketTextStream("hadoop06", 9999)

    lines.foreachRDD(rdd => {
      if (!rdd.isEmpty()) {
        rdd.flatMap(x => x.split(" ")).map((_, 1)).reduceByKey((_ + _)).collect().foreach(println)
      }else{
        println("=====================================")
      }
    })

    streamingContext.start
    streamingContext.awaitTermination
  }
}