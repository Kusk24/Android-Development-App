package com.example.starterapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.starterapplication.ui.theme.StarterApplicationTheme

private var currentText = ""
private var studentFaculty = ""
private var currentnum = 0

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity_LOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "OnCreate Called")
        enableEdgeToEdge()
        setContent {
            StarterApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "OnStart Called")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy Called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("currentText", currentText)
        outState.putString("faculty", studentFaculty)
        outState.putString("currentnum", currentnum.toString())
        super.onSaveInstanceState(outState)
        Log.d(TAG, "OnSaveInstanceState Called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        currentText = savedInstanceState.getString("currentText").toString()
        studentFaculty = savedInstanceState.getString("faculty").toString()
        currentnum = savedInstanceState.getString("currentnum")?.toInt()!!
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "OnRestoreInstanceState Called")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember{ mutableStateOf(currentText)}
    var text2 by remember{ mutableStateOf(studentFaculty)}
    var text3 by rememberSaveable {mutableStateOf(currentnum)}

    Column{
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        TextField(text, onValueChange = {
            text = it
            currentText = text
        })

        TextField(text2, onValueChange = {
            text2 = it
            studentFaculty = text2
        }, label = {Text("Faculty")})

        Text(
            text = text3.toString(),
            modifier = modifier
        )

        Row {
            Button(onClick = {
                text3++
                currentnum--
            }) {
                Text("increase")
            }
            Button(onClick = {
                text3--
                currentnum--
            }) {
                Text("decrease")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarterApplicationTheme {
        Greeting("Android")
    }
}

