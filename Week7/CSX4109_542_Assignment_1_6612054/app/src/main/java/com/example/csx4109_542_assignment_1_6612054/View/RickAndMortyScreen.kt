package com.example.csx4109_542_assignment_1_6612054.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.compose.AsyncImage
import com.example.csx4109_542_assignment_1_6612054.ViewModel.RickAndMortyViewModel

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

            Column {
                Row(modifier = Modifier
                    .height(48.dp)
                    .padding(horizontal = 100.dp)
                    .fillMaxWidth()) {
                    info.value.prev?.let {
                        Button(
                            onClick = {
                                viewModel.loadPage(it)
                                viewModel.minusCurrentPage()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Prev")
                        }
                    }

                    Text(
                        "${currentPage.value}",
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                    )

                    info.value.next?.let {
                        Button(
                            onClick = {
                                viewModel.loadPage(it)
                                viewModel.plusCurrentPage()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Next")
                        }
                    }
                }

                LazyColumn(modifier = modifier) {
                    items(results.value) { item ->
                        Row(modifier.padding(horizontal = 16.dp).fillMaxWidth().height(80.dp).clickable {
                            navController.navigate("DetailPage/${item.id}")
                        }) {
                            AsyncImage(model = item.image, contentDescription = null)

                            Text(
                                item.name,
                                modifier = Modifier.padding(
                                    vertical = 20.dp,
                                    horizontal = 20.dp
                                )
                            )
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 24.dp),
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
                Column {
                    DetailScreen(it)

                    Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text("Back")
                    }
                }

            }
        }
    }


}
