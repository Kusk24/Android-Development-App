package com.example.week_5_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(modifier: Modifier) {
    val viewModel: MainViewModel = viewModel()
    val name = viewModel.name.collectAsState()
    val age = viewModel.age.collectAsState()
    val detail = viewModel.detail.collectAsState()

    val latestName = viewModel.latestName.collectAsState()
    val latestAge = viewModel.latestAge.collectAsState()
    val latestDetail = viewModel.latestDetail.collectAsState()

    Column(modifier = modifier) {
        // name
        FormTextField(name) { viewModel.setName(it) }

        // age
        FormTextField(age, Modifier.padding(top = 24.dp)) { viewModel.setAge(it) }

        // detail
        FormTextField(detail, Modifier.padding(top = 24.dp)) { viewModel.setDetail(it) }

        FormButton(Modifier.padding(top = 24.dp)) { viewModel.saveRequest() }

        LatestRecord(latestName, latestAge, latestDetail)
    }
}

@Composable
fun FormTextField(text: State<String>,
                  modifier: Modifier = Modifier,
                  onTextChanged: (String,) -> Unit) {
    TextField(value = text.value,
            modifier = modifier,
        onValueChange = onTextChanged)
}

@Composable
fun FormButton(modifier: Modifier = Modifier,
               onButtonClicked: () -> Unit) {
    Button(modifier = modifier,
        onClick = onButtonClicked) {

        Text("SUBMIT")
    }
}

@Composable
fun LatestRecord(name: State<String>, age: State<String>, detail: State<String>) {
    Column {
        Text("Name: ${ name.value }")
        Text("Age: ${ age.value }")
        Text("Detail: ${ detail.value }")
    }
}