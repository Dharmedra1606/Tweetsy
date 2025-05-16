package com.dharmendra.tweetsy.ui.tweetScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TweetScreen(category: String) {
    val tweetViewModel: TweetViewModel = hiltViewModel()
    val tweetsState = tweetViewModel.getTweetsData(category).collectAsState()
    val tweets = tweetsState.value

    LazyColumn {
        items(tweets.size) {
            TweetItem(tweet = tweets[it].tweet!!)
        }
    }
}


@Composable
fun TweetItem(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color.Gray),
    ) {
        Text(
            text = tweet,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
