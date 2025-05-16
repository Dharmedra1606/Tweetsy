package com.dharmendra.tweetsy.ui.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dharmendra.tweetsy.api.TweetsyAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val api: TweetsyAPI) : ViewModel() {

    val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories
    init {
        loadCategories()
    }
    fun loadCategories(): StateFlow<List<String>> {

        viewModelScope.launch {
            val response = api.getCategories()
            if (response.isSuccessful) {
                _categories.value = response.body()!!
            }
        }
        return categories
    }
}