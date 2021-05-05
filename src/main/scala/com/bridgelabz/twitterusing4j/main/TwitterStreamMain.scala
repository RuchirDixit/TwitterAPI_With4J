package com.bridgelabz.twitterusing4j.main

import com.bridgelabz.twitterusing4j.configuration.TwitterConfigurationSetup
import com.bridgelabz.twitterusing4j.database.MongoDatabase
import com.bridgelabz.twitterusing4j.printtweets.{GetFavoritesTweets, GetRetweetsCount, GetTweetsStream}

object TwitterStreamMain {
  // getting config details from ev variables
  val consumerKey: String = sys.env("CONSUMER_KEY")
  val consumerSecret: String = sys.env("CONSUMER_SECRET")
  val accessToken: String = sys.env("ACCESS_KEY")
  val accessTokenSecret: String = sys.env("ACCESS_SECRET")
  def main(args: Array[String]): Unit = {
    println("Twitter streaming is started")
    val configurationBuilder = TwitterConfigurationSetup.setConfiguration(
      consumerKey,
      consumerSecret,
      accessToken,
      accessTokenSecret
    )
    MongoDatabase.setCollectionName("retweets")
   // GetTweetsStream.getTweetsBasedOnKeywords(configurationBuilder,"President lang:en")
    GetRetweetsCount.getCountOfRetweets(configurationBuilder,"India")
    //GetFavoritesTweets.getFavoritesTweets(configurationBuilder)
  }
}
