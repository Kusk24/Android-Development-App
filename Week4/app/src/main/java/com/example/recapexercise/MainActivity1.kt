package com.example.recapexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.recapexercise.ui.theme.RecapExerciseTheme

private var currentText = ""
class MainActivity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecapExerciseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    var studentCode = remember { mutableStateOf(currentText) }
                    Column(modifier = Modifier.padding(innerPadding)){

                        studentCodeLabel(studentCode)

                        StatelessTextField(studentCode, onTextChanged = {
                            studentCode.value = it
                            currentText = studentCode.value
                        })
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("studentCode", currentText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        currentText = savedInstanceState.getString("studentCode").toString()
        super.onRestoreInstanceState(savedInstanceState)
    }
}

@Composable
fun studentCodeLabel(studentCode: State<String>){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
        Text("Hello ${studentCode.value}")
    }
}


//@Composable
//fun StudentCodeTextField(modifier: Modifier){
//    var studentCode by rememberSaveable { mutableStateOf(currentText) }
//    Column(modifier = modifier.fillMaxSize().wrapContentWidth(Alignment.CenterHorizontally).wrapContentHeight(
//        Alignment.CenterVertically)){
//
//        TextField(studentCode, onValueChange = {
//            val numbers = listOf('0','1','2','3','4','5','6','7','8','9')
//            var isCharacterContrained = false
//            if(it.length <= 7){
//                for (character in it) {
//                    if(!numbers.contains(character)){
//                        isCharacterContrained = true
//                    }
//                }
//                if (!isCharacterContrained){
//                    studentCode = it
//                    currentText = studentCode
//                }
//            } },
//            label = { Text(text = "Student Code" )},
//            singleLine =  true)
//    }
//}

@Composable
fun StatelessTextField(text: State<String>, onTextChanged : (String) -> Unit){
    TextField(value = text.value,
        onValueChange = onTextChanged)
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//
//}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    RecapExerciseTheme {
//        Greeting("Android")
//    }
//}