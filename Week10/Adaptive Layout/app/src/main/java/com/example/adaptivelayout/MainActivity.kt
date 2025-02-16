package com.example.adaptivelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.adaptivelayout.ui.theme.AdaptiveLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PersonListDetailPane(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun PersonListDetailPane(modifier: Modifier){
    val navigator = rememberListDetailPaneScaffoldNavigator<Person>()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = { },
        detailPane = { },
        extraPane = { }
    )
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.PersonListPane(navigator: ThreePaneScaffoldNavigator<Person>,
                                                  modifier: Modifier){
    AnimatedPane {
        PersonList(modifier = modifier,
            onItemClicked = { person ->
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, person)
            })
    }
}

@Composable
fun PersonList(modifier: Modifier, onItemClicked : (Person) -> Unit){
    val persons = mutableListOf(
        Person(firstName = "JOHN", lastName = "DOE", nickName = "J.D", description = "")
    )

    Column(Modifier.clickable{}){

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
    AdaptiveLayoutTheme {
        Greeting("Android")
    }
}