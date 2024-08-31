package com.example.tweetsy.di

import com.example.tweetsy.api.TweetsyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * we want a one object of any class we can define as a singletone like [@InstallIn(SingletonComponent)]
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTweetyApi(retrofit: Retrofit):TweetsyApi{
        return retrofit.create(TweetsyApi::class.java)
    }
}