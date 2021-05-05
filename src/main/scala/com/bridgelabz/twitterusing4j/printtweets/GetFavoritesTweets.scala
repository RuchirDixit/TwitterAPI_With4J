package com.bridgelabz.twitterusing4j.printtweets

import com.bridgelabz.twitterusing4j.database.MongoDatabase
import com.typesafe.scalalogging.LazyLogging
import twitter4j.{Paging, Status, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

object GetFavoritesTweets extends LazyLogging{

  def getFavoritesTweets(configurationBuilder: ConfigurationBuilder) = {
    val twitterFactory = new TwitterFactory(configurationBuilder.build()).getInstance()
    val favoritesList = twitterFactory.getFavorites("netflix",new Paging(1,30))
    val retweetsList: List[Status] = List.empty
    favoritesList.forEach(tweet => {
      if(tweet.getFavoriteCount > 500) {
        retweetsList :+ tweet.getText
      }
    })
    logger.info("Count of tweets with likes > 500" + retweetsList.size)
    MongoDatabase.save(retweetsList)
  }
}
