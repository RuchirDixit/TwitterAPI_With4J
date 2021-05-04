package com.bridgelabz.twitterusing4j.database

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import twitter4j.Status

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object MongoDatabase extends LazyLogging{

  implicit val system = ActorSystem("MongoClient_Kafka-App")
  implicit val executor: ExecutionContext = system.dispatcher
  val mongoClient: MongoClient = MongoClient()
  val databaseName = sys.env("DATABASENAME")
  // Getting mongodb database
  val database: MongoDatabase = mongoClient.getDatabase(databaseName)
  val collectionName = "tweets"
  // Getting mongodb collection
  val collection: MongoCollection[Document] = database.getCollection(collectionName)
  collection.drop()


  /**
   * To save tweets inside database
   * @param tweets : List of tweets from API
   * @return : Unit
   */
  def save(tweets : List[Status]): Unit = {
    logger.info("Inside save")
    tweets.foreach(status => {
      val documentToBeInserted : Document = Document(status.getText)
      val bindFuture = collection.insertOne(documentToBeInserted).toFuture()
      bindFuture.onComplete {
        case Success(_) => logger.info("Added Successfully!")
        case Failure(exception) => logger.warn(exception.toString)
      }
    })
  }

}
