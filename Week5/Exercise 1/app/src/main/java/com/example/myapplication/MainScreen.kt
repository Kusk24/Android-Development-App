package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

//@Composable
//fun MainScreen(modifier: Modifier) {
//    val viewModel: MainViewModel = viewModel()
//    val studentCode = viewModel.studentCode.collectAsState()
//    Column (modifier = modifier ){
//        Text("Hello ${studentCode.value}")
//        TextField(value = "", onValueChange = { currentText ->
//            viewModel.updateStudentCode(currentText)
//        })
//    }
//}