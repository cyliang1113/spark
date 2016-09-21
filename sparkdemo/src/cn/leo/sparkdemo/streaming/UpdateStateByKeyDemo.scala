package cn.leo.sparkdemo.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka.KafkaUtils

/**
 * 累计统计 从数据流开始累计
 */
object UpdateStateByKeyDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("UpdateStateByKeyDemo")
    conf.setMaster("local[6]")

    val streamingContext = new StreamingContext(conf, Seconds(10))
    
    // update state 要设这checkpoint
    streamingContext.checkpoint("D:/spark-test/sparkStreaming/UpdateStateByKeyDemo/checkpoint")

    // kafka "hadoop06:2181,hadoop07:2181,hadoop08:2181", "FirstGroup", "topic.hello.kafka"
    val topics = Map[String, Int](("topic.hello.kafka", 3))
    val lines = KafkaUtils.createStream(streamingContext, "hadoop06:2181,hadoop07:2181,hadoop08:2181", "FirstGroup", topics, StorageLevel.MEMORY_AND_DISK_2)

    lines.flatMap(x => x._2.split(" ")).map((_, 1)).updateStateByKey(
      (values: Seq[Int], cc: Option[Int]) => {

        val s = values.sum + cc.getOrElse(0)
        Some[Int](s)

      }).print()

    streamingContext.start
    streamingContext.awaitTermination

  }
}