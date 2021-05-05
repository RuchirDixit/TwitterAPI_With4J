package com.bridgelabz.twitterusing4j.printtweets

import com.bridgelabz.twitterusing4j.kafka.{Consumer, Producer}
import com.typesafe.scalalogging.LazyLogging
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

object GetFavoritesTweets extends LazyLogging{

  def getFavoritesTweets(configurationBuilder: ConfigurationBuilder) = {
    Producer
    val twitterFactory = new TwitterFactory(configurationBuilder.build()).getInstance()
    Producer.produceFavoritesTweets(twitterFactory)
    Consumer
  }
}
