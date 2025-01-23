package com.example.week4_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week4_2.ui.theme.Week4_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week4_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var temperature = rememberSaveable { mutableStateOf("") }
                    val currentSelectedUnit = rememberSaveable { mutableStateOf("") }
                    val temperatureResultText = rememberSaveable { mutableStateOf("") }

                    Column(modifier = Modifier.padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(Alignment.CenterVertically)
                    ) {
//                        GreetingStudentCodeText(currentText)
//                        StudentCodeTextField(currentText,
//                            onTextChanged = {
//                            currentText.value = it
//                        })

                        TemperatureTextField(temperature = temperature, onTemperatureChanged = {
                            temperature.value = it
                        })

                        TemperatureRadioGroup(currentSelectedUnit, onRadioButtonClicked = { currentUnit ->
                            currentSelectedUnit.value = currentUnit
                        })

                        TemperatureButton({
                            if (currentSelectedUnit.value == "C") {
                                temperatureResultText.value = ((temperature.value.toFloat() * 1.8) + 32).toString()
                            } else {
                                temperatureResultText.value = ((temperature.value.toFloat() - 32) / 1.8).toString()
                            }
                        })

                        TemperatureResultText(temperatureResultText)
                    }
                }
            }
        }
    }
}

@Composable
fun TemperatureResultText(result: State<String>) {
    Text("Conversion Result: ${ result.value }")
}

@Composable
fun TemperatureButton(onTemperatureButtonClicked: () -> Unit) {
    Button(onClick = onTemperatureButtonClicked) {
        Text("Convert")
    }
}

@Composable
fun TemperatureRadioGroup(currentSelectedUnit: State<String>,
                          onRadioButtonClicked: (String) -> Unit) {
    Row {
        TemperatureRadioButton(unitText = "C",
            currentSelectedUnit = currentSelectedUnit,
            onRadioButtonClicked = onRadioButtonClicked)

        TemperatureRadioButton(unitText = "F",
            currentSelectedUnit = currentSelectedUnit,
            onRadioButtonClicked = onRadioButtonClicked)
    }
}

@Composable
fun TemperatureRadioButton(unitText: String,
                           currentSelectedUnit: State<String>,
                           onRadioButtonClicked: (String) -> Unit) {
    Row {
        RadioButton(selected = currentSelectedUnit.value == unitText, onClick = {
            onRadioButtonClicked(unitText)
        })
        Text(unitText)
    }
}

@Composable
fun TemperatureTextField(temperature: State<String>, onTemperatureChanged: (String) -> Unit) {
    TextField(value = temperature.value, onValueChange = onTemperatureChanged)
}

@Composable
fun GreetingStudentCodeText(studentCode: State<String>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Text("Hello ${ studentCode.value }")
    }
}

@Composable
fun StatelessTextField(text: State<String>, onTextChanged: (String) -> Unit) {
    TextField(value = text.value,
        onValueChange = onTextChanged)
}

@Composable
fun StudentCodeTextField(currentText: State<String>,
                         onTextChanged: (String) -> Unit) {
    Column{
        TextField(value = currentText.value, onValueChange = onTextChanged,
//        {
//            val numbers = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
//            var isCharacterContained = false
//
//            if (it.length <= 7) {
//                for (character in it) {
//                    if (!numbers.contains(character)) {
//                        isCharacterContained = true
//                    }
//                }
//
//                if (!isCharacterContained) {
//                    currentText.value = it
//                }
//            }
//        },
            label = {
            Text("Student Code")
        }
        )
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
    Week4_2Theme {
        Greeting("Android")
    }
}