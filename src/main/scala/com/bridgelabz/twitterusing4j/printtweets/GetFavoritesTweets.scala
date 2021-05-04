package com.bridgelabz.twitterusing4j.printtweets

import com.typesafe.scalalogging.LazyLogging
import twitter4j.{Paging, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

object GetFavoritesTweets extends LazyLogging{

  def getFavoritesTweets(configurationBuilder: ConfigurationBuilder) = {
    val twitterFactory = new TwitterFactory(configurationBuilder.build()).getInstance()
    val favoritesList = twitterFactory.getFavorites("netflix",new Paging(1,30))
    favoritesList.forEach(tweet => {
      if(tweet.getFavoriteCount > 500) {
        logger.info(s"User id: ${tweet.getUser.getScreenName}")
        logger.info(s"Tweet: ${tweet.getText}")
        logger.info(s"Count : ${tweet.getFavoriteCount}")
      }
    })
  }
}
