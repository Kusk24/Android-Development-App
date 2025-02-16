package com.example.listvariation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.listvariation.ui.theme.ListVariationTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListVariationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SwipableListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SampleStickyHeaderList(modifier : Modifier){

    val names = listOf(
        "Austin",
        "Aerial",
        "John",
        "Jenny",
        "Henry",
        "Kevin",
        "Helen",
        "Paul",
        "Peter",
        "Mike",
        "Max",
        "Bob",
        "Ai",
        "Austin",
        "Aerial",
        "John",
        "Jenny",
        "Henry",
        "Kevin",
        "Helen",
        "Paul",
        "Peter",
        "Mike",
        "Max",
        "Bob",
        "Ai"
    )

    val groupedNames = names.groupBy {it.first()}.toSortedMap()


    LazyColumn(modifier = Modifier.padding(16.dp)) {
        groupedNames.forEach { groupedNames ->

            stickyHeader{
                Surface(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
                    Text(
                        text = "Group : ${groupedNames.key}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                    )
                }
            }

            items(groupedNames.value) { groupItem ->
                NameItem(groupItem)
            }
        }

    }
}

@Composable
fun NameItem(name : String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp)
    ){
        Text(text = name, modifier = Modifier.padding(8.dp),
            fontSize = 18.sp)
    }
}

@Composable
fun SampleGridItem(modifier : Modifier){

    val imageUrls = listOf(
        "https://picsum.photos/300/200",
        "https://picsum.photos/400/250",
        "https://picsum.photos/500/300",
        "https://picsum.photos/350/400",
        "https://picsum.photos/450/350",
        "https://picsum.photos/600/450",
        "https://picsum.photos/500/500",
        "https://picsum.photos/300/200",
        "https://picsum.photos/400/250",
        "https://picsum.photos/500/300",
        "https://picsum.photos/350/400",
        "https://picsum.photos/450/350",
        "https://picsum.photos/600/450",
        "https://picsum.photos/500/500"
    )

    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = modifier.padding(16.dp)) {
        items(imageUrls) { imageUrl ->
            ImageItem(imageUrl)
        }
    }
}

@Composable
fun ImageItem(imageUrl : String) {
    AsyncImage(model = imageUrl,
        contentDescription = null)
}