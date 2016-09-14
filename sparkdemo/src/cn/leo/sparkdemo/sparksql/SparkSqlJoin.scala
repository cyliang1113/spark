package cn.leo.sparkdemo.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SparkSqlJoin {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkSqlJoin");
    conf.setMaster("local");

    val sc = new SparkContext(conf);
    val sqlContext = new SQLContext(sc);

    val df = sqlContext.read.json("file:/D:/spark-test/sparkSql/data/student.json");

  }
}