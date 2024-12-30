package com.example.classexercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val latestInfo by viewModel.latestInfo.collectAsState() // Observe latestInfo
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var issue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Employee Issue Form", style = MaterialTheme.typography.titleLarge)

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = age, // State variable for the text input
            onValueChange = { age = it }, // Update the state when text changes
            label = { Text("age") }, // Label for the text field
            modifier = Modifier.padding(vertical = 8.dp) // Modifier for padding
        )
        TextField(
            value = issue,
            onValueChange = { issue = it },
            label = { Text("Issue Details") },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                viewModel.submitForm(name, age, issue)
                name = ""
                age = ""
                issue = ""
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Latest Information Submitted:", style = MaterialTheme.typography.titleMedium)
        Text(latestInfo ?: "No information available yet.")
    }
}
