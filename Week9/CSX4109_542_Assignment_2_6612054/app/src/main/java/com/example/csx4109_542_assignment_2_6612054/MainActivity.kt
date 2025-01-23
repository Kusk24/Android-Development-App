package com.example.csx4109_542_assignment_2_6612054

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.csx4109_542_assignment_2_6612054.View.RickAndMortyScreen
import com.example.csx4109_542_assignment_2_6612054.ui.theme.CSX4109_542_Assignment_2_6612054Theme

//Name - Win Yu Maung
//ID - 6612054
//Sec - 542

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSX4109_542_Assignment_2_6612054Theme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xFFF2FBFF)) { innerPadding ->
                    RickAndMortyScreen(modifier = Modifier.padding(innerPadding))
                }
            }
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
    CSX4109_542_Assignment_2_6612054Theme {
        Greeting("Android")
    }
}