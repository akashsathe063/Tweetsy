package com.example.tweetsy.api

import com.example.tweetsy.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

/**
 * in retrofit implementation interface we can create for define end points of a api
 * and also create a methods as per the api end points.
 */
interface TweetsyApi {
    //@Header is used to dynamic data
    @GET("/v3/b/66cef434e41b4d34e4266d54?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path")category: String):Response<List<TweetListItem>>
//@Headers used to static data
    @GET("/v3/b/66cef434e41b4d34e4266d54?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories():Response<List<String>>
}