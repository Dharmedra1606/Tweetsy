package com.dharmendra.tweetsy.ui.tweetScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun TweetScreen(category: String) {
    val tweetViewModel: TweetViewModel = hiltViewModel()
    val tweetsState = tweetViewModel.getTweetsData(category).collectAsState()
    val tweets = tweetsState.value

    val refreshState = produceState(initialValue = 0f) {
        while (true) {
            delay(16L) // ~60fps
            value = (value + 10f) % 360f
        }
    }
    if (tweets.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {

            Image(imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh",
                alignment = Alignment.Center,
                modifier = Modifier.padding(16.dp)
                    .size(60.dp)
                    .rotate(refreshState.value.toFloat()),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.Black))

        }
    }else{

        LazyColumn {
            items(tweets.size) {
                TweetItem(tweet = tweets[it].tweet!!)
            }
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
