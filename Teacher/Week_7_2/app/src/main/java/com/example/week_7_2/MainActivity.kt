package com.example.week_7_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.week_7_2.ui.theme.PredictionViewModel
import com.example.week_7_2.ui.theme.Week_7_2Theme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }
    install(Logging) {
        level = LogLevel.ALL
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week_7_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PredictionAppScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PredictionAppScreen(modifier: Modifier) {
    val navController = rememberNavController()
    val viewModel: PredictionViewModel = viewModel()
    val currentName = viewModel.currentName.collectAsState()
    val predictionResponse = viewModel.predictionResponse.collectAsState()

    NavHost(navController = navController,
        startDestination = "Name",
        modifier = modifier) {
        composable(route = "Name") {
            NameScreen(currentName = currentName,
                onTextChanged = { name ->
                    viewModel.updateCurrentName(name)
                },
                onPredictionButtonClicked = { name ->
                    navController.navigate("prediction/$name")
                })
        }

        composable(route = "prediction/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backstackEntry ->
            val name = backstackEntry.arguments?.getString("name") ?: ""
            viewModel.predictName(name)
            PredictionResultScreen(name, predictionResponse)
        }
    }
}

@Composable
fun NameScreen(currentName: State<String>,
               onTextChanged: (String) -> Unit,
               onPredictionButtonClicked: (String) -> Unit) {
    Column {
        TextField(value = currentName.value, onValueChange = onTextChanged)
        Button(onClick = {
            onPredictionButtonClicked(currentName.value)
        }) {
            Text("PREDICT")
        }
    }
}

@Composable
fun PredictionResultScreen(name: String,
                           predictionResponse: State<PredictionResponse>) {

    Column {
        Text("Hello $name")
        if (predictionResponse.value.country.isNotEmpty()) {
            Text("Nationality: ${ predictionResponse.value.country[0].country_id }")
            Text("Probability: ${ predictionResponse.value.country[0].probability }")

            Text("Other Guess")

            LazyColumn {
                items(predictionResponse.value.country) { item ->
                    Text("${ item.country_id }: ${ item.probability }")
                }
            }
        }
    }

}

@Composable
fun MainAppScreen(modifier: Modifier) {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val currentName = viewModel.currentName.collectAsState()

    NavHost(navController = navController,
        startDestination = "InputName",
        modifier = modifier) {

        composable(route = "InputName") {
            InputNameScreen(currentName = currentName,
                onTextChanged = { name ->
                    viewModel.updateCurrentName(name)
                },
                onButtonClicked = {
                    navController.navigate("Welcoming")
                })
        }

        composable(route = "Welcoming") {
            WelcomingScreen(currentName)
        }

//        composable(route = "Page1") {
//            PageOneScreen(onButtonClicked = {
//                navController.navigate("Page2")
//            })
//        }
//
//        composable(route = "Page2") {
//            PageTwoScreen()
//        }

    }
}

@Composable
fun InputNameScreen(currentName: State<String>,
                    onTextChanged: (String) -> Unit,
                    onButtonClicked: () -> Unit) {
    Column {
        TextField(value = currentName.value, onValueChange = onTextChanged)
        Button(onClick = onButtonClicked) {
            Text("OK")
        }
    }
}

@Composable
fun WelcomingScreen(name: State<String>) {
    Text("Hello ${ name.value }")
}

@Composable
fun PageOneScreen(onButtonClicked: () -> Unit) {
    Column {
        Text("Page 1")
        Button(onClick = onButtonClicked) {
            Text("Go to Page 2")
        }
    }
}

@Composable
fun PageTwoScreen() {
    Text("Page 2")
}