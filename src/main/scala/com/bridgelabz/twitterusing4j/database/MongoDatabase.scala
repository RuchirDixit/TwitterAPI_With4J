package com.bridgelabz.twitterusing4j.database

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object MongoDatabase extends LazyLogging{

  implicit val system = ActorSystem("MongoClient_Kafka-App")
  implicit val executor: ExecutionContext = system.dispatcher
  val mongoClient: MongoClient = MongoClient()
  val databaseName = sys.env("DATABASENAME")
  // Getting mongodb database
  val database: MongoDatabase = mongoClient.getDatabase(databaseName)
  var collection: MongoCollection[Document] = _
  def setCollectionName(collectionName : String): Unit = {
    collection = database.getCollection(collectionName)
    collection.drop()
  }



  /**
   * To save tweets inside database
   * @param tweets : List of tweets from API
   * @return : Unit
   */
  def save(tweets : String): Unit = {
    val documentToBeInserted : Document = Document("Tweet" -> tweets)
    val bindFuture = collection.insertOne(documentToBeInserted).toFuture()
    bindFuture.onComplete{
      case Success(_) => println("Added Successfully!")
      case Failure(exception) => println(exception)
    }
  }

}
