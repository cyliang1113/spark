package cn.leo.sparkdemo.streaming

import scala.collection.immutable.Set

import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream.toPairDStreamFunctions
import org.apache.spark.streaming.kafka.KafkaUtils

import kafka.serializer.StringDecoder

object KafkaDirect {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KafkaDirect")
    conf.setMaster("local[6]")

    val streamingContext = new StreamingContext(conf, Seconds(10))

    val kafkaParams = Map[String, String](("metadata.broker.list", "hadoop06:9092,hadoop07:9092,hadoop08:9092"))
    val topics = Set[String]("topic.hello.kafka")
    
    val lines = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](streamingContext, kafkaParams, topics)

    lines.flatMap(x => x._2.split(" ")).map((_, 1)).reduceByKey((_ + _)).print()

    streamingContext.start
    streamingContext.awaitTermination
  }
}