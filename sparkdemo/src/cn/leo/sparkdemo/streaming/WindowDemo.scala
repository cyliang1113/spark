package cn.leo.sparkdemo.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

object WindowDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WindowDemo")
    conf.setMaster("local[6]")

    val streamingContext = new StreamingContext(conf, Seconds(10))
    streamingContext.checkpoint("D:/spark-test/sparkStreaming/UpdateStateByKeyDemo/checkpoint")

    // kafka "hadoop06:2181,hadoop07:2181,hadoop08:2181", "FirstGroup", "topic.hello.kafka"
    val topics = Map[String, Int](("topic.hello.kafka", 3))
    val lines = KafkaUtils.createStream(streamingContext, "hadoop06:2181,hadoop07:2181,hadoop08:2181", "FirstGroup", topics, StorageLevel.MEMORY_AND_DISK_2)

    lines.print

    // 每二十秒 统计一次前三十秒的内容   滑动窗体
    lines.flatMap(x => x._2.split(" ")).map((_, 1))
      .reduceByKeyAndWindow((a: Int, b: Int) => a + b, Seconds(30), Seconds(20)).print()

    streamingContext.start
    streamingContext.awaitTermination
  }
}