package com.dharmendra.tweetsy.ui.categoryScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dharmendra.tweetsy.ui.mainActivity.MainViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject


@Composable
fun CategoryScreen(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val categoriesState = mainViewModel.categories.collectAsState()
    val categories = categoriesState.value

    val refreshState = produceState(initialValue = 0f) {
        while (true) {
            delay(16L) // ~60fps
            value = (value + 10f) % 360f
        }
    }

    if (categories.isEmpty()) {

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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues( 8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.size) {
                CategoryItem(navController, category = categories[it])
            }
        }

    }
}

//@Preview(showBackground = true)
@Composable
fun CategoryItem (navController: NavController, category: String  ) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(160.dp)
            .clickable {
                navController.navigate("tweet/$category")
            }
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp))
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            fontSize = 18.sp,
            modifier = Modifier.padding(0.dp, 20.dp),
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}
