package com.example.classexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.classexercise.ui.theme.ClassExerciseTheme
//import com.example.com.example.classexercise.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClassExerciseTheme {
                // Pass MainViewModel to MainScreen
                val mainViewModel: MainViewModel = viewModel()
                MainScreen(viewModel = mainViewModel)
            }
        }
    }
}
