package cn.leo.sparkdemo.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

/**
 * 按照黑名单 过滤数据
 */
object BlackListDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("BlackListDemo")
    conf.setMaster("local[6]")

    val streamingContext = new StreamingContext(conf, Seconds(10))

    // 黑名单 可以从外部获得
    val blackList = Array(("cpp", 1), ("hadoop", 1))
    val blackListRdd = streamingContext.sparkContext.parallelize(blackList);

    //"hadoop06:2181,hadoop07:2181,hadoop08:2181",  "FirstGroup",  "topic.hello.kafka"
    val topics = Map[String, Int](("topic.hello.kafka", 3))
    val lines = KafkaUtils.createStream(streamingContext, "hadoop06:2181,hadoop07:2181,hadoop08:2181", "FirstGroup", topics, StorageLevel.MEMORY_AND_DISK_2)

    // 模拟的数据格式为: 时间\t名称  如: [20160919  hello]
    val kvLines = lines.map(
      l => {
        val str = l._2;
        val arr = str.split("\t")
        (arr(1), str)
      })

    kvLines.transform(data => data.leftOuterJoin(blackListRdd)).filter(l => {
      println(l)
      val tag = l._2._2.getOrElse(0)
      if (tag != 1)
        true
      else
        false
    }).map(l => l._2._1).print()

    streamingContext.start
    streamingContext.awaitTermination
  }
}