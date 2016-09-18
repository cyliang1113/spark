package cn.leo.sparkdemo.streaming

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.storage.StorageLevel

object KafkaReceiver {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KafkaReceiver")
    conf.setMaster("local[6]")

    val streamingContext = new StreamingContext(conf, Seconds(10))

    // streamingContext, "hadoop21:2181,hadoop22:2181,hadoop23:2181", "FirstGroup", "topic.hello.kafka"
    val topics = Map[String, Int](("topic.hello.kafka", 3))
    val lines = KafkaUtils.createStream(streamingContext, "hadoop06:2181,hadoop07:2181,hadoop08:2181", "FirstGroup", topics, StorageLevel.MEMORY_AND_DISK_2)

    //lines.flatMapValues(x => x.split(" ")).map((_, 1)).reduceByKey((_ + _)).print()
    lines.flatMap(x => x._2.split(" ")).map((_, 1)).reduceByKey((_ + _)).print()

    streamingContext.start
    streamingContext.awaitTermination
  }
}