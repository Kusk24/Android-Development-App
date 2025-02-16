package com.example.week_11_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week_11_2.ui.theme.Week_11_2Theme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week_11_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UniversitySearchingScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun UniversitySearchingScreen(modifier: Modifier) {
    val navigator = rememberSupportingPaneScaffoldNavigator()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    // val viewModel = MainViewModel() // not OK
    val viewModel: MainViewModel = viewModel()
    val keyword = viewModel.keyword.collectAsState()
    val universities = viewModel.universities.collectAsState()

    val country = viewModel.country.collectAsState()
    val similarUniversities = viewModel.similarUniversities.collectAsState()

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = { UniversitySearchingMainPane(navigator = navigator,
            currentKeyword = keyword,
            onKeywordChanged = { currentKeyword ->
                viewModel.updateKeyword(currentKeyword)
            },
            onSearchButtonClicked = {
                viewModel.searchByKeyword(keyword.value)
            },
            universities = universities,
            modifier = modifier) },
        supportingPane = { SimilarUniversitySupportingPane(navigator = navigator,
            country = country,
            similarUniversities = similarUniversities,
            onSupportingPaneLoaded = {
                viewModel.searchByCountry(country.value)
            },
            modifier = modifier) }
    )
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.UniversitySearchingMainPane(navigator: ThreePaneScaffoldNavigator<Nothing>,
                                                               currentKeyword: State<String>,
                                                               onKeywordChanged: (String) -> Unit,
                                                               onSearchButtonClicked: () -> Unit,
                                                               universities: State<List<University>>,
                                                               modifier: Modifier) {

    val isSupportingPaneHidden = navigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Hidden
    AnimatedPane {
        UniversitySearchingMainScreen(currentKeyword = currentKeyword,
                                      onKeywordChanged = onKeywordChanged,
            onSearchButtonClicked = onSearchButtonClicked,
            universities = universities,
            isShowSimilarButtonShown = isSupportingPaneHidden,
            onShowSimilarButtonClicked = {
                navigator.navigateTo(SupportingPaneScaffoldRole.Supporting)
            },
            modifier = modifier)
    }
}

@Composable
fun UniversitySearchingMainScreen(currentKeyword: State<String>,
                                  onKeywordChanged: (String) -> Unit,
                                  onSearchButtonClicked: () -> Unit,
                                  universities: State<List<University>>,
                                  isShowSimilarButtonShown: Boolean,
                                  onShowSimilarButtonClicked: () -> Unit,
                                  modifier: Modifier) {

    Column(modifier = modifier.padding(16.dp)) {
        TextField(value = currentKeyword.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { currentText ->
            onKeywordChanged(currentText)
        })

        Button(onClick = onSearchButtonClicked,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Text(text = "SEARCH")
        }

        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(universities.value) { university ->
                UniversityItem(university)
                //HorizontalDivider(thickness = 2.dp)
            }
        }

        if (isShowSimilarButtonShown) {
            Button(onClick = {
                onShowSimilarButtonClicked()
            }) {
                Text(text = "SHOW SIMILAR")
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.SimilarUniversitySupportingPane(navigator: ThreePaneScaffoldNavigator<Nothing>,
                                                                   country: State<String>,
                                                                   similarUniversities: State<List<University>>,
                                                                   onSupportingPaneLoaded: () -> Unit,
                                                                   modifier: Modifier) {

    AnimatedPane {
        //onSupportingPaneLoaded()

        SimilarUniversityScreen(country = country,
            similarUniversities = similarUniversities,
            modifier = modifier)
    }
}

@Composable
fun SimilarUniversityScreen(country: State<String>,
                            similarUniversities: State<List<University>>,
                            modifier: Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (similarUniversities.value.isNotEmpty()) {
            Text(text = "Universities in ${ country.value}",
                fontSize = TextUnit(24f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))

            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(similarUniversities.value) { university ->
                    UniversityItem(university)
                    //HorizontalDivider(thickness = 2.dp)
                }
            }
        }
    }
}

@Composable
fun UniversityItem(university: University) {
    Card (modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ))
    {
        Text(text = university.name,
            color = Color.White,
            modifier = Modifier.padding(8.dp))
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun PersonSupportingPaneLayoutScreen(modifier: Modifier) {
    val person = Person(
        firstName = "John",
        lastName = "Wick",
        nickname = "J.W.",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eu lobortis magna. Vivamus condimentum lacus nec ligula consequat, ac lobortis eros consectetur. Aenean turpis augue, aliquam ac tellus quis, consequat malesuada leo. Maecenas euismod placerat nisi eu auctor. Sed malesuada elit ac nisi luctus varius ut vel odio. Mauris in posuere metus, id maximus est. Sed semper nibh urna, a eleifend lacus sodales nec. Interdum et malesuada fames ac ante ipsum primis in faucibus. Proin facilisis commodo nisi sit amet lobortis. Mauris scelerisque elit in urna scelerisque hendrerit. Nullam vehicula tellus nulla, ut auctor leo consectetur non. Vivamus non est laoreet, gravida nunc et, commodo dui."
    )

    val comments = listOf(
        Comment(text = "Comment 1", date = Date()),
        Comment(text = "Comment 2", date = Date()),
        Comment(text = "Comment 3", date = Date()),
        Comment(text = "Comment 4", date = Date()),
        Comment(text = "Comment 5", date = Date())
    )

    val navigator = rememberSupportingPaneScaffoldNavigator()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = { PersonInformationPane(person = person, navigator = navigator, modifier = modifier) },
        supportingPane = { CommentSupportingPane(comments = comments, modifier = modifier) }
    )
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.PersonInformationPane(person: Person,
                                                         navigator: ThreePaneScaffoldNavigator<Nothing>,
                                                         modifier: Modifier) {

    val isSupportingPaneHidden = navigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Hidden
    AnimatedPane {
        PersonInformationScreen(person = person,
            modifier = modifier,
            isShowCommentButtonShown = isSupportingPaneHidden,
            onShowCommentButtonClicked = {
                navigator.navigateTo(SupportingPaneScaffoldRole.Supporting)
            })
    }
}

@Composable
fun PersonInformationScreen(person: Person,
                            modifier: Modifier,
                            isShowCommentButtonShown: Boolean,
                            onShowCommentButtonClicked: () -> Unit) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = person.nickname,
            fontSize = TextUnit(32f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))

        Text(text = "Name: ${ person.firstName } ${ person.lastName }")
        Text(text = person.description)

        if (isShowCommentButtonShown) {
            Button(onClick = onShowCommentButtonClicked, modifier = Modifier.padding(top = 16.dp).fillMaxWidth()) {
                Text(text = "SHOW COMMENT")
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldScope.CommentSupportingPane(comments: List<Comment>,
                                                         modifier: Modifier) {

    AnimatedPane {
        LazyColumn(modifier = modifier.padding(16.dp)) {
            items(comments) { comment ->
                CommentItem(comment)
                HorizontalDivider(thickness = 2.dp)
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Column {
        Text(text = comment.date.toString())
        Text(text = comment.text,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold)
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
    Week_11_2Theme {
        Greeting("Android")
    }
}