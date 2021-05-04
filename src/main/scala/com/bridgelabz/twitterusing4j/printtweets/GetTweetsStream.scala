package com.bridgelabz.twitterusing4j.printtweets

import com.bridgelabz.twitterusing4j.database.MongoDatabase
import com.bridgelabz.twitterusing4j.database.query.QueryBuilder
import com.typesafe.scalalogging.LazyLogging
import twitter4j.conf.ConfigurationBuilder
import twitter4j.{Query, Status, TwitterException, TwitterFactory}

import scala.util.control.Breaks.break

object GetTweetsStream extends LazyLogging{

  /**
   * Use methods from Twitter API to get data
   * @param configurationBuilder : ConfigurationBuilder
   * @return : Unit
   */
  def getTweetsBasedOnKeywords(configurationBuilder: ConfigurationBuilder, keywordsToQuery : String) : Unit = {
    logger.info("Streaming Twitter Data From Twitter")
    var tweets : List[Status] = List.empty
    try {
      val twitterFactory = new TwitterFactory(configurationBuilder.build()).getInstance()
      val result = QueryBuilder.searchQuery(keywordsToQuery,twitterFactory)
      var count = 0
      while(result.hasNext){
        count = count + 1
        tweets :+ result.getTweets
        if(count > 100) {
          break
        }
      }
    }
  catch {
    case nullPointerException: NullPointerException =>
      println(nullPointerException.printStackTrace())
      throw new Exception("instance is initialized with null")
    case twitterException: TwitterException =>
      println(twitterException.printStackTrace())
      throw new Exception("Failed to search tweets")
    case _  =>
      MongoDatabase.save(tweets)
  }
  }
}
