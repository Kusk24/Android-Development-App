package com.example.myapplication

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
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MemeScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Demolist(modifier: Modifier){
    val viewModel: MainViewModel = viewModel()
    val url = viewModel.url.collectAsState()
    val name = viewModel.name.collectAsState()

//    LazyColumn(modifier = modifier){
//        AsyncImage(model = url.value, contentDescription = null)
//
//        Text(name.value)
//    }
//    LazyColumn(modifier = modifier ){
//        items(imageUrl) {item ->
//
//            AsyncImage(model = item, contentDescription = null)
//            Text(item)
//
//        }
//    }
}
@Composable
fun MainScreen(modifier: Modifier){
    val viewModel: MainViewModel = viewModel()
    val url = viewModel.url.collectAsState()

//    Text(modifier =  modifier , text = url.value)

    Column(modifier = modifier){
        AsyncImage(model = url.value, contentDescription = null)

        Text(text = url.value)

        Button(modifier = Modifier.fillMaxWidth(), onClick = {viewModel.loadData()}) {
            Text("Load")
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}