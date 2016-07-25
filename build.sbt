scalaVersion := "2.10.5"
version := "0.13.12"
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.0"
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.6.0"
resolvers += "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"
libraryDependencies += "datastax" % "spark-cassandra-connector" % "1.6.0-s_2.10"

