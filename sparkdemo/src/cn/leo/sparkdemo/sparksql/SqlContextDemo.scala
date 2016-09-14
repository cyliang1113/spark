package cn.leo.sparkdemo.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SqlContextDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SqlContextDemo");
    conf.setMaster("local");

    val sc = new SparkContext(conf);
    val sqlContext = new SQLContext(sc);

    val df = sqlContext.read.json("D:\\spark-test\\sparkSql\\data\\people.json");

    df.show();

    // 默认parquet格式
    //    df.write.save("D:\\spark-test\\sparkSql\\data\\peopleOut2")

    //    df.write.format("json").save("D:\\spark-test\\sparkSql\\data\\peopleOut")
  }
}