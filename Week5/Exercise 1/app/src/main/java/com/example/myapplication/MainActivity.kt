package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    CounterScreen(Modifier.padding(innerPadding))
//                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

//@Composable
//fun CounterScreen(modifier: Modifier) {
//    val viewModel : MainViewModel = viewModel()
//    Column {
//        Text("Counter : ${viewModel.counter.value}")
//
//        Button(onClick = {viewModel.add()}) { Text("Add")}
//    }
//}