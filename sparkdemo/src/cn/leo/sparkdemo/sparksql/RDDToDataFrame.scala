package cn.leo.sparksqldemo

import scala.reflect.runtime.universe

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object RDDToDataFrame {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("RDDToDataFrame");

    conf.setMaster("local");

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val rdd = sc.textFile("D:\\spark-test\\sparkSql\\data\\people.txt")
    val df = rdd.map { x =>
      {
        val arr = x.split("\t");
        People(arr(0).trim().toString(), arr(1).trim().toInt)
      }
    }.toDF()
    
    df.registerTempTable("t_people")
    sqlContext.sql("select * from t_people t").show()

  }
}

