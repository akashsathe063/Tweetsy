package com.example.tweetsy.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tweetsy.viewmodels.DetailViewModel

@Preview
@Composable
fun DetailsScree(modifier: Modifier = Modifier) {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val tweets = detailViewModel.tweets.collectAsState()
    if (tweets.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.headlineLarge)
        }
    } else {
        LazyColumn {
            items(tweets.value) {
                TweetListItem(tweet = it.text)
            }
        }
    }
}

@Preview
@Composable
private fun TweetListItem(modifier: Modifier = Modifier, tweet: String = "") {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = tweet,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}