package com.example.week10_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week10_2.ui.theme.Week10_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week10_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UniversityListDetailPane(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun UniversityListDetailPane(modifier: Modifier) {
    val navigator = rememberListDetailPaneScaffoldNavigator<University>()
    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    val viewModel: MainViewModel = viewModel()
    val keyword = viewModel.keyword.collectAsState()
    val universities = viewModel.universities.collectAsState()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = { UniversitySearchListPane(navigator = navigator,
            keyword = keyword,
            onKeywordValueChanged = { currentKeyword ->
                viewModel.updateKeyword(currentKeyword)
            },
            onSearchButtonClicked = { keyword ->
                viewModel.search(keyword)
            },
            universities = universities,
            modifier = modifier) },
        detailPane = { UniversityDetailPane(navigator, modifier) }
    )
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.UniversitySearchListPane(navigator: ThreePaneScaffoldNavigator<University>,
                                                            keyword: State<String>,
                                                            onKeywordValueChanged: (String) -> Unit,
                                                            onSearchButtonClicked: (String) -> Unit,
                                                            universities: State<List<University>>,
                                                            modifier: Modifier) {
    AnimatedPane {
        UniversitySearchList(keyword = keyword,
                            onKeywordValueChanged = onKeywordValueChanged,
                            onSearchButtonClicked = onSearchButtonClicked,
                            universities = universities,
                            onUniversityItemClicked = { university ->
                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, university)
                            },
                            modifier = modifier)
    }
}

@Composable
fun UniversitySearchList(keyword: State<String>,
                         onKeywordValueChanged: (String) -> Unit,
                         onSearchButtonClicked: (String) -> Unit,
                         universities: State<List<University>>,
                         onUniversityItemClicked: (University) -> Unit,
                         modifier: Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        TextField(value = keyword.value, onValueChange = onKeywordValueChanged)
        Button(onClick = {
            onSearchButtonClicked(keyword.value)
        }) {
            Text(text = "SEARCH")
        }

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(universities.value) { university ->
                UniversityItem(university, onUniversityItemClicked)

                HorizontalDivider(thickness = 2.dp, color = Color.Gray)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.UniversityDetailPane(navigator: ThreePaneScaffoldNavigator<University>,
                                                    modifier: Modifier) {
    val university = navigator.currentDestination?.content
    AnimatedPane {
        if (university != null) {
            UniversityDetail(university, modifier)
        }
    }
}

@Composable
fun UniversityDetail(university: University,
                     modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = university.name,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(24f, TextUnitType.Sp)
        )

        Text(text = "Country: ${ university.country }")
        Text(text = "Webpage: ${ university.web_pages[0] }")
    }
}

@Composable
fun UniversityItem(university: University, onUniversityItemClicked: (University) -> Unit) {
    Text(text = university.name,
        fontWeight = FontWeight.Bold,
        fontSize = TextUnit(18f, TextUnitType.Sp),
        modifier = Modifier.clickable {
            onUniversityItemClicked(university)
        })
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun PersonListDetailPane(modifier: Modifier) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Person>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = { PersonListPane(navigator, modifier) },
        detailPane = { PersonDetailPane(navigator, modifier) },
        extraPane = { GreetingExtraPane(navigator, modifier) }
    )
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.PersonListPane(navigator: ThreePaneScaffoldNavigator<Person>,
                                                  modifier: Modifier) {

    AnimatedPane {
        PersonList(modifier, onPersonItemClicked = { person ->
            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, person)
        })
    }
}

@Composable
fun PersonList(modifier: Modifier, onPersonItemClicked: (Person) -> Unit) {
    val persons = mutableListOf(
        Person(firstName = "JOHN", lastName = "DOE", nickname = "J.D.", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eu lobortis magna. Vivamus condimentum lacus nec ligula consequat, ac lobortis eros consectetur. Aenean turpis augue, aliquam ac tellus quis, consequat malesuada leo. Maecenas euismod placerat nisi eu auctor. Sed malesuada elit ac nisi luctus varius ut vel odio. Mauris in posuere metus, id maximus est. Sed semper nibh urna, a eleifend lacus sodales nec. Interdum et malesuada fames ac ante ipsum primis in faucibus. Proin facilisis commodo nisi sit amet lobortis. Mauris scelerisque elit in urna scelerisque hendrerit. Nullam vehicula tellus nulla, ut auctor leo consectetur non. Vivamus non est laoreet, gravida nunc et, commodo dui."),
        Person(firstName = "JANE", lastName = "DEE", nickname = "JANET.", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eu lobortis magna. Vivamus condimentum lacus nec ligula consequat, ac lobortis eros consectetur. Aenean turpis augue, aliquam ac tellus quis, consequat malesuada leo. Maecenas euismod placerat nisi eu auctor. Sed malesuada elit ac nisi luctus varius ut vel odio. Mauris in posuere metus, id maximus est. Sed semper nibh urna, a eleifend lacus sodales nec. Interdum et malesuada fames ac ante ipsum primis in faucibus. Proin facilisis commodo nisi sit amet lobortis. Mauris scelerisque elit in urna scelerisque hendrerit. Nullam vehicula tellus nulla, ut auctor leo consectetur non. Vivamus non est laoreet, gravida nunc et, commodo dui.")
    )

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Person List",
            fontSize = TextUnit(32f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold)

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(persons) { person ->
                PersonItem(person = person,
                    onPersonItemClicked = onPersonItemClicked)

                HorizontalDivider(thickness = 2.dp, color = Color.Black)
            }
        }
    }
}

@Composable
fun PersonItem(person: Person, onPersonItemClicked: (Person) -> Unit) {
    Text(text = "${ person.firstName } (${ person.nickname })",
        modifier = Modifier.clickable {
            onPersonItemClicked(person)
        })
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.PersonDetailPane(navigator: ThreePaneScaffoldNavigator<Person>,
                                                    modifier: Modifier) {
    val person = navigator.currentDestination?.content
    AnimatedPane {
        if (person != null) {
            PersonDetail(person, modifier, onGreetingButtonClicked = { person ->
                navigator.navigateTo(ListDetailPaneScaffoldRole.Extra, person)
            })
        } else {
            Text(text = "No Person Selected", modifier = modifier.padding(16.dp))
        }
    }
}

@Composable
fun PersonDetail(person: Person,
                 modifier: Modifier,
                 onGreetingButtonClicked: (Person) -> Unit) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Name: ${ person.firstName } ${ person.lastName }")
        Text("Nickname: ${ person.nickname }")
        Text("Description: ${ person.description }")

        Button(modifier = modifier.padding(top = 16.dp)
                                  .fillMaxWidth(),
            onClick = {
                onGreetingButtonClicked(person)
        }) {
            Text("GREET")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.GreetingExtraPane(navigator: ThreePaneScaffoldNavigator<Person>,
                                                     modifier: Modifier) {
    val person = navigator.currentDestination?.content
    AnimatedPane {
        if (person != null) {
            GreetingNickname(person, modifier)
        }
    }
}

@Composable
fun GreetingNickname(person: Person, modifier: Modifier) {
    Text(text = "Hello ${ person.nickname }. Welcome to the Application",
        modifier = modifier.padding(16.dp),
        fontWeight = FontWeight.Bold,
        fontSize = TextUnit(24f, TextUnitType.Sp))
}