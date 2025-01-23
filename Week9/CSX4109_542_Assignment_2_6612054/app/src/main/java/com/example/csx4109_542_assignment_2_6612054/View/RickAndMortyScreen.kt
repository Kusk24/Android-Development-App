package com.example.csx4109_542_assignment_2_6612054.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.compose.AsyncImage
import com.example.csx4109_542_assignment_2_6612054.ViewModel.RickAndMortyViewModel

@Composable
fun RickAndMortyScreen(modifier: Modifier) {

    val navController = rememberNavController()
    val viewModel: RickAndMortyViewModel = viewModel()
    val results = viewModel.results.collectAsState()
    val currentPage = viewModel.currentPage.collectAsState()
    val info = viewModel.info.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "MainPage",
        modifier = modifier
    ) {

        composable(route = "MainPage") {

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val (box1, prev, page, next) = createRefs()

                val line1 = createGuidelineFromTop(75.dp)

                info.value.prev?.let {
                    Button(
                        onClick = {
                            viewModel.loadPage(it, "Prev")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.constrainAs(prev) {
                            top.linkTo(parent.top)
                            end.linkTo(page.start, 10.dp)
                            bottom.linkTo(line1)
                        }
                    ) {
                        Text("Previous")
                    }
                }

                Text(
                    text = "${currentPage.value}",
                    modifier = Modifier.constrainAs(page) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(line1)
                    }
                )

                info.value.next?.let {
                    Button(
                        onClick = {
                            viewModel.loadPage(it, "Next")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.constrainAs(next) {
                            top.linkTo(parent.top)
                            start.linkTo(page.end, 10.dp)
                            bottom.linkTo(line1)
                        }
                    ) {
                        Text("Next")
                    }
                }

                LazyColumn(modifier = Modifier
                    .constrainAs(box1) {
                        top.linkTo(line1, 25.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }) {

                    items(results.value) { item ->
                        ConstraintLayout(
                            modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .clickable {
                                    navController.navigate("DetailPage/${item.id}")
                                }) {

                            val (imageC, nameC) = createRefs()
                            val line2 = createGuidelineFromTop(0.35f)

                            AsyncImage(model = item.image,
                                contentDescription = null,
                                modifier = Modifier.constrainAs(imageC) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start, 16.dp)
                                })

                            Text(
                                item.name,
                                modifier = Modifier.constrainAs(nameC) {
                                    start.linkTo(imageC.end, 10.dp)
                                    top.linkTo(line2)
                                }
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 0.5.dp,
                            color = Color.Black
                        )
                    }
                }

            }
        }

        composable(
            route = "DetailPage/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backstackEntry ->
            val itemId = backstackEntry.arguments?.getInt("itemId") ?: 0
            val selectedItem = results.value.find { it.id == itemId }

            selectedItem?.let {
                DetailScreen(it)
            }
        }
    }
}