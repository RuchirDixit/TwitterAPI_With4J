package com.bridgelabz.twitterstreaming.main

import com.bridgelabz.twitterstreaming.printtweets.PrintTweets
import com.danielasfregola.twitter4s.entities.enums.Language
import com.danielasfregola.twitter4s.entities.{AccessToken, ConsumerToken}
import com.danielasfregola.twitter4s.{TwitterRestClient, TwitterStreamingClient}
import com.typesafe.scalalogging.LazyLogging

object TwitterStreamingClientAPI extends App with LazyLogging {

  val consumerToken = ConsumerToken(key = sys.env("CONSUMER_KEY"), secret = sys.env("CONSUMER_SECRET"))
  val accessToken = AccessToken(key = sys.env("ACCESS_KEY"), secret = sys.env("ACCESS_SECRET"))

  val restClient = TwitterRestClient(consumerToken, accessToken)
  val streamingClient = TwitterStreamingClient(consumerToken, accessToken)

  streamingClient.sampleStatuses(languages = Seq(Language.English))(PrintTweets.printRetweetStatus)
}
