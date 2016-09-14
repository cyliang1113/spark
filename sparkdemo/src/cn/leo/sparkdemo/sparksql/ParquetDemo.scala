package cn.leo.sparkdemo.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * parquet文件 在本地处理 运行会报错
 */
object ParquetDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ParquetDemo");
    conf.setMaster("local");

    val sc = new SparkContext(conf);
    val sqlContext = new SQLContext(sc);

    val df = sqlContext.read.parquet("file:/D:/spark-test/sparkSql/data/users.parquet");
    df.rdd.collect();
  }
}