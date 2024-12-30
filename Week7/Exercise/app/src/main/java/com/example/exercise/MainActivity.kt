package com.example.exercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exercise.ui.theme.ExerciseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExerciseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainAppScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
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

    NavHost(
        navController = navController,
        startDestination = "InputNameScreen",
        modifier = modifier) {

        composable(route = "InputNameScreen"){
            InputNameScreen(currentName = currentName,
                onTextChanged = {name -> viewModel.updateCurrentName(name)},
                onButtonClicked = {navController.navigate("Welcoming")})
        }

        composable(route = "Welcoming"){
            WelcomingScreen(currentName)
        }
    }

//    NavHost(
//        navController = navController,
//        startDestination = "Page1",
//        modifier = modifier) {
//
//        composable(route = "Page1"){
//            PageOneScreen(onButtonClicked = {navController.navigate("Page2")})
//        }
//
//        composable(route = "Page2"){
//            PageTwoScreen()
//        }
//    }
}

@Composable
fun WelcomingScreen(name: State<String>){
    Text("Hello ${name.value}")
}

@Composable
fun InputNameScreen(currentName: State<String>,
                    onTextChanged: (String) -> Unit,
                    onButtonClicked: () -> Unit){
    Column{

        TextField(value = currentName.value,
            onValueChange = onTextChanged)

        Button(onClick = onButtonClicked){
            Text("OK")
        }

    }

}

@Composable
fun PageOneScreen(onButtonClicked: () -> Unit){

    Column {
        Text("Page 1")


        Button(onClick = onButtonClicked ) {
            Text("Go to Page 2")
        }
    }

}


@Composable
fun PageTwoScreen() {
    Text("Page2")
}
@Composable
fun PredictionAppScreen(modifier: Modifier){

    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val currentName = viewModel.currentName.collectAsState()


    NavHost(
        navController = navController,
        startDestination = "Name",
        modifier = modifier) {

        composable(route = "prediction/{name}",
            arguments = listOf(navArgument("name"){
            type = NavType.StringType
        })){backstackEntry ->
            val name = backstackEntry.arguments?.getString("name") ?: ""}
    }
}

@Composable
fun NameScreen(currentName: State<String>, onTextChanged: (String) -> Unit, onButtonClicked: () -> Unit) {
    Column {
        TextField(value = currentName.value, onValueChange = onTextChanged)
        Button(onClick = onButtonClicked) {
            Text("PREDICT")
        }
    }
}

@Composable
fun PredictionScreen(name: String, predictionResponse: State<PredictionResponse>){
    Column{
        Text("Hello $name")
        if (predictionResponse.value.country_id.isNotEmpty()){
            Text("Nationality: ${predictionResponse.value.country_id[0].country_id}")
            Text("Prediction: ${predictionResponse.value.country_id[0].probability}")
        }
    }
}
