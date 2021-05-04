package com.bridgelabz.twitterusing4j.configuration

import twitter4j.TwitterException
import twitter4j.conf.ConfigurationBuilder

object TwitterConfigurationSetup {
  /**
   *
   * @param consumerKey : Consumer Key for Twitter account
   * @param consumerSecret : Consumer Secret Key for Twitter account
   * @param accessToken : Access Token Key for Twitter account
   * @param accessTokenSecret : Access Token secret Key for Twitter account
   * @return
   */
  def setConfiguration(consumerKey: String, consumerSecret: String, accessToken: String, accessTokenSecret: String): ConfigurationBuilder = {
    try {
      val configurationBuilder = new ConfigurationBuilder()
      configurationBuilder
        .setDebugEnabled(true)
        .setOAuthConsumerKey(consumerKey)
        .setOAuthConsumerSecret(consumerSecret)
        .setOAuthAccessToken(accessToken)
        .setOAuthAccessTokenSecret(accessTokenSecret)
      configurationBuilder
    } catch {
      case nullPointerException: NullPointerException =>
        println(nullPointerException.printStackTrace())
        throw new Exception("Null fields")
      case twitterException: TwitterException =>
        println(twitterException.printStackTrace())
        throw new Exception("Twitter connection error")
    }
  }
}
