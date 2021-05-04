package com.bridgelabz.twitterusing4j.main

import com.bridgelabz.twitterusing4j.configuration.TwitterConfigurationSetup
import com.bridgelabz.twitterusing4j.printtweets.GetTweetsStream

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
    GetTweetsStream.getTweetsBasedOnKeywords(configurationBuilder,"President lang:en")
  }
}
