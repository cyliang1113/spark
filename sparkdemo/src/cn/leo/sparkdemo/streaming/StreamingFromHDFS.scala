package cn.leo.sparkdemo.streaming

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.hadoop.mapreduce.InputFormat
import org.apache.hadoop.mapred.FileInputFormat

object StreamingFromHDFS {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("StreamingFromHDFS")

    //    conf.setMaster("local")

    val streamingContext = new StreamingContext(conf, Seconds(10))

    val lines = streamingContext.textFileStream("hdfs://ns1/sparkStreaming/data/")

    //    val lines = streamingContext.textFileStream("D:/spark-test/sparkStreaming/data")

    lines.flatMap(x => x.split("\t")).map((_, 1)).reduceByKey(_ + _).print()

    streamingContext.start
    streamingContext.awaitTermination
  }
}