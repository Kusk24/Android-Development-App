package com.example.week_13_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.week_13_2.ui.theme.Week_13_2Theme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week_13_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SwipeableListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SwipeableListScreen(modifier: Modifier) {
    val someItems = (1..100).map { "Item $it" }
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(someItems) { item ->
            SwipeableItem(item)
        }
    }
}

@Composable
fun SwipeableItem(item: String) {
    val scope = rememberCoroutineScope()
    val offsetX = remember(item) { mutableStateOf(0f) }
    val threshold = -50f

    Box(modifier = Modifier.padding(16.dp)
        .fillMaxWidth()
        .height(60.dp)) {

        Row(modifier = Modifier.fillMaxWidth()
            .height(60.dp)
            .background(color = Color.LightGray),
            horizontalArrangement = Arrangement.End) {
          Button(onClick = {}) {
              Text(text = "DELETE")
          }
        }

        Box(modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .offset(offsetX.value.dp)
            .background(color = Color.White)
            .pointerInput(Unit) {
                detectHorizontalDragGestures (
                    onHorizontalDrag = { _, dragAmount ->
                        offsetX.value = (offsetX.value + dragAmount).coerceIn(-120f, 0f)
                    },
                    onDragEnd = {
                        if (offsetX.value < threshold) {
                            offsetX.value = -120f
                        } else {
                            offsetX.value = 0f
                        }
                    }
                )
            }
        ) {
            Text(text = item,
                fontSize = 18.sp,
                modifier = Modifier.wrapContentHeight(Alignment.CenterVertically))
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun StickyPersonNameListScreen(modifier: Modifier) {
    val viewModel: MainViewModel = viewModel()
    val personNames = viewModel.personNames.collectAsState()

    val groupedPersonNames = personNames.value.groupBy { it.first.first() }

    Column(modifier = modifier.padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            groupedPersonNames.forEach { groupedName ->
                stickyHeader {
                    Surface(modifier = Modifier.fillParentMaxWidth()) {
                        Text(
                            text = "Alphabet: ${groupedName.key}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(18.dp)
                        )
                    }
                }

                items(groupedName.value) { personName ->
                    PersonNameItem(personName)
                }
            }
        }

        Button(
            onClick = { viewModel.loadPerson() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "LOAD PERSON")
        }
    }
}

@Composable
fun PersonNameItem(name: PersonName) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "${name.title} ${name.first} ${name.last}",
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun SampleStickyHeaderList(modifier: Modifier) {
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

    val groupedNames = names.groupBy { it.first() }
        .toSortedMap()

    LazyColumn(modifier = modifier.padding(start = 16.dp, end = 16.dp)) {
        groupedNames.forEach { groupedName ->
            stickyHeader {
                Surface(
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) {
                    Text(
                        text = "Group: ${groupedName.key}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = modifier.padding(bottom = 16.dp)
                    )
                }
            }

            items(groupedName.value) { groupedItem ->
                NameItem(groupedItem)
            }
        }
    }
}

@Composable
fun NameItem(name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = name, modifier = Modifier.padding(8.dp),
            fontSize = 18.sp
        )
    }
}

@Composable
fun SampleGridList(modifier: Modifier) {
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
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.padding(16.dp)
    ) {
        items(imageUrls) { imageUrl ->
            ImageItem(imageUrl)
        }
    }
}

@Composable
fun ImageItem(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = ""
    )
}