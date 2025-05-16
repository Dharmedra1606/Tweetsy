package com.dharmendra.tweetsy.api

import com.dharmendra.tweetsy.model.Categories
import com.dharmendra.tweetsy.model.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface TweetsyAPI {

    @GET("v3/b/68257fd18a456b79669e1479")
    suspend fun getCategories(
        @Query("meta") meta: Boolean = false
    ): Response<Categories>

    @GET("v3/b/6824a02d8561e97a5013c1b3")
    suspend fun getTweets(
        @Header("X-JSON-Path") category: String,
        @Query("meta") meta: Boolean = false,
    ): Response<List<Tweet>>

}