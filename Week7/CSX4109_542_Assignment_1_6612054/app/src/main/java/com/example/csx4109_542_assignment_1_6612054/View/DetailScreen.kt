package com.example.csx4109_542_assignment_1_6612054.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.csx4109_542_assignment_1_6612054.Model.CharacterResult

@Composable
fun DetailScreen(result: CharacterResult){
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()){
        AsyncImage(result.image, contentDescription = null,
            modifier = Modifier.width(400.dp).height(400.dp))

        Text("Name: ${result.name}")

        Text("Status: ${result.status}")

        Text("Species: ${result.species}")
    }
}