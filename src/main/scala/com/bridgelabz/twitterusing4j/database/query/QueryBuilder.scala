package com.bridgelabz.twitterusing4j.database.query

import twitter4j.{Query, QueryResult, Twitter}

object QueryBuilder {

  def searchQuery(keywordsToSearch : String,twitterFactory : Twitter) : QueryResult = {
    val query = new Query(keywordsToSearch)
    twitterFactory.search(query)
  }
}
