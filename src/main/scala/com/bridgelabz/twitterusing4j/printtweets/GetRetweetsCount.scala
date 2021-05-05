package com.bridgelabz.twitterusing4j.printtweets

import com.bridgelabz.twitterusing4j.database.MongoDatabase
import com.bridgelabz.twitterusing4j.database.query.QueryBuilder
import com.bridgelabz.twitterusing4j.kafka.{Consumer, Producer}
import com.typesafe.scalalogging.LazyLogging
import twitter4j.{Status, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

object GetRetweetsCount extends LazyLogging{

  def getCountOfRetweets(configurationBuilder: ConfigurationBuilder,keywordsToQuery : String) = {
    Producer
    val twitterFactory = new TwitterFactory(configurationBuilder.build()).getInstance()
    Producer.productRetweets(twitterFactory,keywordsToQuery)
    Consumer
  }
}
