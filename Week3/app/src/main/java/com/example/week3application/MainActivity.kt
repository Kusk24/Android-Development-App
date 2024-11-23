package com.example.week3application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.week3application.ui.theme.Week3ApplicationTheme

private var myText = ""
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3ApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text2: String by rememberSaveable {mutableStateOf(myText)}
    var isDialogOpening by remember { mutableStateOf(false) }
//    Column{
//        Text(
//            text = "Hello $name!",
//            modifier = modifier,
//            color = Color.Blue,
//            fontSize = TextUnit(24f, TextUnitType.Sp),
//            fontStyle = Italic,
//            fontWeight = Bold
//        )
//
//        TextField(
//            value = text2,
////            onValueChange = { text2 = it},
//            onValueChange = {myText -> text2 = myText},
//            placeholder = {Text("Enter Text Field")}
//        )
//
//
//        Button(onClick = { isDialogOpening = true },) { Text("Click Here") }
//
//        if (isDialogOpening) {
//            Dialog( onDismissRequest = {} ){
//                Card (modifier = Modifier.fillMaxWidth().height(200.dp)){
//                    Text("Dialog Shown Here", modifier = Modifier.background(Color.Yellow).fillMaxSize().wrapContentHeight(Alignment.CenterVertically).wrapContentWidth(Alignment.CenterHorizontally))
//                }
//            }
//        }
//
//        Row(modifier = modifier){
//            Text("Hello", modifier = Modifier.padding(start = 16.dp))
//            Text("World")
//            Text("!!!")
//        }
//
//        Box(modifier = modifier){
//            Text("Hello")
//            Text("World")
//            Text("!!!")
//        }
//    }
//    Row( modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 24.dp)){
//        Text("Hello")
//        Text("World")
//        Text("!!!")
//        TextField("", onValueChange = {}, modifier = Modifier.padding(start = 24.dp))
//    }

    var currentText by remember{ mutableStateOf(myText) }
    Column (modifier = Modifier.padding(start = 125.dp, top = 350.dp, end = 75.dp)){
        Row {
            Text("Name", modifier = Modifier.padding(top = 20.dp))
            TextField(value = currentText,
                onValueChange = {myText -> currentText = myText},
                modifier = Modifier.padding(start = 20.dp),
//                TextFieldColors = TextFieldDefaults.colors().
            )
        }

        Button(onClick = {}, modifier = modifier.padding(start = 60.dp), colors = ButtonColors(
            contentColor = Color.Black,
            containerColor = Color.Red,
            disabledContainerColor = Color.Red,
            disabledContentColor = Color.Red
        )
        ) {
            Text("Click")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week3ApplicationTheme {
        Greeting("Android")
    }
}