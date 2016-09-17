package cn.leo.sparkdemo.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Duration
import org.apache.spark.streaming.Seconds

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount")

    val streamingContext = new StreamingContext(conf, Seconds(1))
    
    // 客户端socket
    val lines = streamingContext.socketTextStream("hadoop23", 9999)
    
    lines.flatMap(x => x.split(" ")).map((_, 1)).reduceByKey((_ + _)).print()
    
    streamingContext.start
    streamingContext.awaitTermination
  }
}