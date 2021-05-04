package com.bridgelabz.twitterstreaming.printtweets

import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage
import com.typesafe.scalalogging.LazyLogging

object PrintTweets extends LazyLogging {

  // To print whether retweets exists
  def printRetweetStatus: PartialFunction[StreamingMessage, Unit] = {
    case tweet: Tweet =>
      logger.info(tweet.retweeted.toString)
  }

  // print text of the tweet
  def printTweetText: PartialFunction[StreamingMessage, Unit] = {
    case tweet: Tweet => println(tweet.text)
  }
}
