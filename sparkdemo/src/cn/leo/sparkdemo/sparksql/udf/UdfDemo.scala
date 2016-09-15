package cn.leo.sparkdemo.sparksql.udf

import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.Row

object UdfDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("UdfDemo")
    conf.setMaster("local")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val rdd = sc.textFile("D:\\spark-test\\sparkSql\\data\\people.txt")
    val rowRdd = rdd.map(x => Row(x.split("\t")(0), x.split("\t")(1).trim().toInt))

    val schema = StructType(Array(StructField("name", StringType), StructField("age", IntegerType)))
    
    val df = sqlContext.createDataFrame(rowRdd, schema)
    df.registerTempTable("t_people")
    
    // 定义udf
    sqlContext.udf.register("add10", (x: Int) => x + 10)
    
    // 使用udf
    sqlContext.sql("select name, add10(age) as age1 from t_people").show()
    
   

  }
}