package cn.leo.sparkdemo.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField

object RDDToDataFrameBySchema {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RDDToDataFrameBySchema");
    conf.setMaster("local");

    val sc = new SparkContext(conf);
    val sqlContext = new SQLContext(sc);

    val rdd = sc.textFile("D:\\spark-test\\sparkSql\\data\\people.txt");
    val rowRDD = rdd.map(x => Row(x.split("\t")(0), x.split("\t")(1).trim))

    // schema
    val schemaString = "name	age";  // 可以定义在配置文件中
    val schema = StructType(schemaString.split("\t").map(fieldName => StructField(fieldName, StringType, true)))
    
    val df = sqlContext.createDataFrame(rowRDD, schema);
    
    // df.show();
    // df.selectExpr("name", "age + 10").show()
    df.registerTempTable("t_people");
    sqlContext.sql("select * from t_people where age > 18").show();
    sqlContext.sql("select sum(age)/count(*) from t_people").show();
    
    sc.stop();

  }
}