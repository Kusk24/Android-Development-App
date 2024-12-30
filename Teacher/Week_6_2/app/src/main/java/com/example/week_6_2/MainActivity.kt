package com.example.week_6_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.week_6_2.ui.theme.Week_6_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week_6_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MemeScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DemoList(modifier: Modifier) {
    val imageUrls = listOf("https://i.imgflip.com/30b1gx.jpg",
        "https://i.imgflip.com/1ur9b0.jpg",
        "https://i.imgflip.com/1ihzfe.jpg")

    LazyColumn(modifier = modifier) {
        items(imageUrls) { item ->
            AsyncImage(model = item, contentDescription = null)
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier) {
    val viewModel: MainViewModel = viewModel()
    val url = viewModel.url.collectAsState()

    Column(modifier = modifier) {
        AsyncImage(model = url.value,
            contentDescription = null)

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.loadData() }) {

            Text("LOAD")
        }

        Text(text = url.value)
    }
}
