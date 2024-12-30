package com.example.myapplication

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun MemeScreen(modifier: Modifier){
    val viewModel: MemeViewModel = viewModel()
    val memes = viewModel.memes.collectAsState()

    LazyColumn(modifier = modifier){
        items(memes.value) {items ->
            AsyncImage(model = items.url, contentDescription = null)
            Text(text = items.name)
        }
    }
}

