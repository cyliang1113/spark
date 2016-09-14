package cn.leo.sparkdemo.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

object SparkSqlAgg {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkSqlAgg");

    conf.setMaster("local");

    val sc = new SparkContext(conf);
    val sqlContext = new SQLContext(sc);

    val df = sqlContext.read.json("file:/D:/spark-test/sparkSql/data/student.json");
 
    df.groupBy("name").agg(sum("sorce").as("ss")).filter("ss > 150").show;
  }

}