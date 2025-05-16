package com.dharmendra.tweetsy.ui.mainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dharmendra.tweetsy.ui.categoryScreen.CategoryScreen
import com.dharmendra.tweetsy.ui.theme.TweetsyTheme
import com.dharmendra.tweetsy.ui.tweetScreen.TweetScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TweetsyTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = "Tweetsy")
                    })
                }){
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }
    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "category") {
            composable(route = "category"){
                CategoryScreen(navController)
            }
            composable(route = "tweet/{category}") {
                val category = it.arguments?.getString("category")
                category?.let {
                    TweetScreen(category = category)
                }
            }
        }

    }
}
