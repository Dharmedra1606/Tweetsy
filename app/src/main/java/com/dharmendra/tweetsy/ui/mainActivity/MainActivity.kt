package com.dharmendra.tweetsy.ui.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dharmendra.tweetsy.ui.categoryScreen.CategoryScreen
import com.dharmendra.tweetsy.ui.theme.TweetsyTheme
import com.dharmendra.tweetsy.ui.tweetScreen.TweetScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TweetsyTheme {
                App()
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
