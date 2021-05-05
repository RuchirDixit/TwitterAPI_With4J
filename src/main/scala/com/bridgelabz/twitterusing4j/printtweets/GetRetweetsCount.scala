package com.bridgelabz.twitterusing4j.printtweets

import com.bridgelabz.twitterusing4j.database.MongoDatabase
import com.bridgelabz.twitterusing4j.database.query.QueryBuilder
import com.typesafe.scalalogging.LazyLogging
import twitter4j.{Status, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

object GetRetweetsCount extends LazyLogging{

  def getCountOfRetweets(configurationBuilder: ConfigurationBuilder,keywordsToQuery : String) = {
    val twitterFactory = new TwitterFactory(configurationBuilder.build()).getInstance()
    val result = QueryBuilder.searchQuery(keywordsToQuery,twitterFactory)
    val tweets = result.getTweets
    val retweetsList: List[Status] = List.empty
    tweets.forEach(tweet =>{
      if(tweet.getRetweetCount > 500){
        retweetsList :+ tweet.getText
      }
    })
    logger.info("Size of retweets > 500" + retweetsList.size)
    MongoDatabase.save(retweetsList)
  }
}
