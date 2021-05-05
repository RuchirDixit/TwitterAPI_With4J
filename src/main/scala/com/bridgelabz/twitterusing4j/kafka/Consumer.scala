package com.bridgelabz.twitterusing4j.kafka

import java.util.Properties

import com.bridgelabz.twitterusing4j.database.MongoDatabase
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.KafkaConsumer
import play.api.libs.json.{JsObject, Json}

import scala.collection.JavaConverters._
object Consumer extends LazyLogging{
  val props:Properties = new Properties()
  props.put("group.id", "test")
  props.put("bootstrap.servers","localhost:9092")
  props.put("key.deserializer",
    "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer",
    "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("enable.auto.commit", "true")
  props.put("auto.commit.interval.ms", "1000")
  val consumer = new KafkaConsumer(props)
  val topics = List("twitterdata")
  try {
    consumer.subscribe(topics.asJava)
    var content = ""
    while (true) {
      val records = consumer.poll(10)
      for (record <- records.asScala) {
        logger.info("Looping over records!")
        content = record.value()
        MongoDatabase.save(content)
      }
    }

  }
  catch{
    case e:Exception => e.printStackTrace()
  }finally {
    consumer.close()
  }
}
