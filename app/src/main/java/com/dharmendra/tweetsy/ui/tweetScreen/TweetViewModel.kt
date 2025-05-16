package com.dharmendra.tweetsy.ui.tweetScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dharmendra.tweetsy.api.TweetsyAPI
import com.dharmendra.tweetsy.model.Tweet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(private val api: TweetsyAPI) : ViewModel() {

    private val _tweets = MutableStateFlow<List<Tweet>>(emptyList())
    val tweets: StateFlow<List<Tweet>> = _tweets

    fun getTweetsData(category: String): StateFlow<List<Tweet>> {
        viewModelScope.launch {
            val response = api.getTweets("tweets[?(@.category==\"$category\")]")
            if (response.isSuccessful) {
                _tweets.value = response.body()!!
            }
        }
        return tweets
    }
}