package com.example.week4_2

import android.health.connect.datatypes.units.Temperature
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week4_2.ui.theme.Week4_2Theme

var C = false
var F = false
var temp = 0
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week4_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    var temperature = remember{ mutableStateOf("")}
                    var currentSelectedUnit = remember { mutableStateOf("") }
                    var temperatureResultText = remember { mutableStateOf("") }

                    Column(modifier = Modifier.padding(innerPadding).fillMaxSize().verticalScroll(rememberScrollState()).wrapContentWidth(Alignment.CenterHorizontally).wrapContentHeight(Alignment.CenterVertically)){
                        TemperatureTextField(temperature = temperature, onTemperatureChanged = {
                            temperature.value = it
                        })

                        TemperatureRadioButton(currentSelectedUnit = currentSelectedUnit , onRadioButtonClicked = { currentUnit -> currentSelectedUnit.value = currentUnit })
                    }

                    TemperatureButton({
                        if (currentSelectedUnit.value == "C"){
                            temperatureResultText.value = (((temperature.value.toFloat()) * 1.8) + 32).toString()
                        }else {
                        temperatureResultText.value = (((temperature.value.toFloat()) -32) / 18).toString()
                    }
                    })

                    TemperatureResultText(temperatureResultText)
                }
            }
        }
    }
}

@Composable
fun TemperatureButton(onRadioButtonClicked: () -> Unit){

}
@Composable
fun TemperatureRadioGroup(currentSelectedUnit: State<String>, onRadioButtonClicked: () -> Unit) {
    Row{
        TemperatureRadioButton(unitText = "C", currentSelectedUnit = currentSelectedUnit, onRadioButtonClicked = onRadioButtonClicked )
        TemperatureRadioButton(unitText = "F", currentSelectedUnit = currentSelectedUnit, onRadioButtonClicked = onRadioButtonClicked )

    }
}

@Composable
fun TemperatureResultText(result: State<String>){
    Text("Conversation Result ${result.value}")
}

@Composable
fun InputText(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){

        if (C){
            var calculatedTemp = temp*1.8 + 32
            Text((calculatedTemp).toString())
        } else if (F) {
            var calculatedTemp = (temp-32)/18
            Text((calculatedTemp).toString())
        }
    }
}
@Composable
fun TemperatureRadioButton(unitText: String, currentSelectedUnit: State<String>, onRadioButtonClicked: () -> Unit){

    Row {
        RadioButton(selected = currentSelectedUnit.value == unitText, onClick = {onRadioButtonClicked(UnitText)})
    }
}
@Composable
fun TemperatureTextField(temperature: State<String>, onTemperatureChanged: (String) -> Unit){
    TextField(value = temperate.value, onValueChange = onTemperatureChanged)

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