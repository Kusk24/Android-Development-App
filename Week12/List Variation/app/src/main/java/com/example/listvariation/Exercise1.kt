package com.example.listvariation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Exercise1(modifier : Modifier){

        val viewModel: ResponseViewModel = viewModel()
        val person = viewModel.personNames.collectAsState()

        val groupedNames = person.value.groupBy { it.first.first() }.toSortedMap()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            groupedNames.forEach { groupedNames ->

                stickyHeader {
                    Surface(modifier = Modifier.fillParentMaxWidth().padding(top = 16.dp)) {
                        Text(
                            text = "Alphabet : ${groupedNames.key}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                        )
                    }
                }

                items(groupedNames.value) { personName ->
                    PersonNameItem(personName)
                }
            }
        }

        Button(
            onClick = { viewModel.loadResults() },
            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally).padding(bottom = 20.dp)) {
            Text("Load Person")
        }
    }
}

@Composable
fun PersonNameItem(name : PersonName){
    Card(modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.Cyan, contentColor = Color.Red)){
            Text(text = name.title + " " + name.first + " " + name.last,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp))
    }
}



@Composable
fun SwipableListScreen(modifier : Modifier){
    val someItem = (1 .. 100).map {"Item $it"}

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(someItem){ item ->
            SwipeableItem(item)
        }
    }
}

@Composable
fun SwipeableItem(item: String) {
    val scope = rememberCoroutineScope()
    val offsetX = remember { mutableStateOf(0f) }
    val threshold = -80f
    val ColorButton = remember { mutableStateOf(Color.Blue)}

    Box(modifier = Modifier.padding(16.dp).fillMaxWidth().height(60.dp)) {
        // DELETE button (background)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.LightGray),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { ColorButton.value = (Color.Red) }, colors = ButtonDefaults.buttonColors(ColorButton.value)) {
                Text(text = "DELETE")
            }
        }

        // Swipeable foreground item
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .offset(offsetX.value.dp)
                .background(color = Color.White)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            offsetX.value = (offsetX.value + dragAmount).coerceIn(-120f, 0f)
                        },
                        onDragEnd = {
                            if (offsetX.value < threshold) {
                                offsetX.value = -120f  // Fully reveal DELETE
                            } else {
                                offsetX.value = 0f  // Reset position
                            }
                        }
                    )
                }
        ) {
            Text(
                text = item,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


