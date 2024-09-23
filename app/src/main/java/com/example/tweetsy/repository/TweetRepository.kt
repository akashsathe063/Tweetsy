package com.example.tweetsy.repository

import com.example.tweetsy.api.TweetsyApi
import com.example.tweetsy.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * the repository is a simple class inside which we can define a methode that
 * where do you get want to get data
 */
class TweetRepository @Inject constructor(private val tweetsyApi: TweetsyApi) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = tweetsyApi.getCategories()
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let {
                _categories.emit(it)
            }
        }
    }

    suspend fun getTweets(categories: String) {
        val response = tweetsyApi.getTweets("tweets[?(@.category==\"$categories\")]")
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let {
                _tweets.emit(it)
            }
        }
    }
}