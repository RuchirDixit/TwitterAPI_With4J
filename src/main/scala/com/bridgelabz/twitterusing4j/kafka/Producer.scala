package com.bridgelabz.twitterusing4j.kafka

import java.util.Properties

import com.bridgelabz.twitterusing4j.database.query.QueryBuilder
import com.bridgelabz.twitterusing4j.printtweets.GetRetweetsCount.logger
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import twitter4j.conf.ConfigurationBuilder
import twitter4j.{Paging, Status, Twitter, TwitterFactory}

object Producer extends LazyLogging{
  val props:Properties = new Properties()
  props.put("bootstrap.servers","localhost:9092")
  props.put("key.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks","all")
  val producer = new KafkaProducer[String, String](props)
  val topic = "twitterdata"

  def produceFavoritesTweets(twitterFactory : Twitter) = {
    val favoritesList = twitterFactory.getFavorites("netflix",new Paging(1,30))
    try{
      favoritesList.forEach(tweet => {
        if(tweet.getFavoriteCount > 500) {
          val record = new ProducerRecord[String,String](topic, tweet.getText)
          producer.send(record)
        }
      })

    }
    catch{
      case e:Exception => e.printStackTrace()
    }finally {
      producer.close()
    }
  }
  def productRetweets(twitterFactory : Twitter,keywordsToQuery : String) = {
    val result = QueryBuilder.searchQuery(keywordsToQuery,twitterFactory)
    val tweets = result.getTweets
    try {
      tweets.forEach(tweet =>{
        if(tweet.getRetweetCount > 500){
          val record = new ProducerRecord[String,String](topic, tweet.getText)
          producer.send(record)
        }
      })
    }
    catch{
      case e:Exception => e.printStackTrace()
    }finally {
      producer.close()
    }
  }
}
