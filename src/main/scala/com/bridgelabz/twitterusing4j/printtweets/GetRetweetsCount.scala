package com.bridgelabz.twitterusing4j.printtweets

import com.bridgelabz.twitterusing4j.database.query.QueryBuilder
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

object GetRetweetsCount {

  def getCountOfRetweets(configurationBuilder: ConfigurationBuilder,keywordsToQuery : String) = {
    val twitterFactory = new TwitterFactory(configurationBuilder.build()).getInstance()
    val result = QueryBuilder.searchQuery(keywordsToQuery,twitterFactory)
    val tweets = result.getTweets
    tweets.forEach(tweet =>{
      if(tweet.getRetweetCount > 500){
        println("Status:" + tweet.getText + " Count:" + tweet.getRetweetCount)
      }
    })
  }
}
