import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._
import com.datastax.spark.connector._
import  org.apache.spark.sql

object Program {
  def main(args: Array[String]) = {
val conf = new SparkConf().setMaster("local").setAppName("My App").set("spark.cassandra.connection.host","172.18.0.2")

val sc = new SparkContext(conf) 
val roadtrip_rdd = sc.cassandraTable("test","roadtrip")
val sizes= roadtrip_rdd.map( line => (line.getString("origin_city"),1) ).reduceByKey((a,b) => a+b);
sizes.take(5)
val sum_distances=roadtrip_rdd.map( line => (line.getString("origin_city"),line.getString("distance").toInt) ).reduceByKey((a,b) => a+b);
val averageResults = sum_distances.join(sizes).mapValues( tuple =>  tuple._1 / tuple._2).collect();
val rdd_averageResults = sc.parallelize(averageResults);
rdd_averageResults.saveToCassandra("test","average_distances",SomeColumns("origin_city","average_distance"));
  }
}

