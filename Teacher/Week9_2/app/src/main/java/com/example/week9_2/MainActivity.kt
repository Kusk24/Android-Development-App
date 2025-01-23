package com.example.week9_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.week9_2.ui.theme.Week9_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week9_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Exercise3(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Exercise3(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (spreadTitle, spreadA, spreadB, spreadC,
            spreadInsideTitle, spreadInsideA, spreadInsideB, spreadInsideC,
            weightedTitle, weightedA, weightedB, weightedC,
            packedTitle, packedA, packedB, packedC) = createRefs()

        // Spread
        Text(text = "Spread",
            modifier = modifier.constrainAs(spreadTitle) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top)
            })

        createHorizontalChain(spreadA, spreadB, spreadC, chainStyle = ChainStyle.Spread)
        ChainItem(itemText = "A",
            modifier = Modifier.constrainAs(spreadA) {
                top.linkTo(spreadTitle.bottom)
            })

        ChainItem(itemText = "B",
            modifier = Modifier.constrainAs(spreadB) {
                top.linkTo(spreadTitle.bottom)
            })

        ChainItem(itemText = "C",
            modifier = Modifier.constrainAs(spreadC) {
                top.linkTo(spreadTitle.bottom)
            })

        // Spread Inside
        Text(text = "Spread Inside",
            modifier = modifier.constrainAs(spreadInsideTitle) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(spreadA.bottom, 16.dp)
            })

        createHorizontalChain(spreadInsideA, spreadInsideB, spreadInsideC,
            chainStyle = ChainStyle.SpreadInside)

        ChainItem(itemText = "A",
            modifier = Modifier.constrainAs(spreadInsideA) {
                top.linkTo(spreadInsideTitle.bottom)
            })

        ChainItem(itemText = "B",
            modifier = Modifier.constrainAs(spreadInsideB) {
                top.linkTo(spreadInsideTitle.bottom)
            })

        ChainItem(itemText = "C",
            modifier = Modifier.constrainAs(spreadInsideC) {
                top.linkTo(spreadInsideTitle.bottom)
            })

        // Weight
        Text(text = "Weight",
            modifier = modifier.constrainAs(weightedTitle) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(spreadInsideA.bottom, 16.dp)
            })

        createHorizontalChain(weightedA, weightedB, weightedC,
            chainStyle = ChainStyle.Spread)

        ChainItem(itemText = "A",
            modifier = Modifier.constrainAs(weightedA) {
                horizontalChainWeight = 1f
                width = Dimension.fillToConstraints
                top.linkTo(weightedTitle.bottom)
            })

        ChainItem(itemText = "B",
            modifier = Modifier.constrainAs(weightedB) {
                horizontalChainWeight = 2f
                width = Dimension.fillToConstraints
                top.linkTo(weightedTitle.bottom)
            })

        ChainItem(itemText = "C",
            modifier = Modifier.constrainAs(weightedC) {
                horizontalChainWeight = 3f
                width = Dimension.fillToConstraints
                top.linkTo(weightedTitle.bottom)
            })

        // Packed
        Text(text = "Packed",
            modifier = modifier.constrainAs(packedTitle) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(weightedA.bottom, 16.dp)
            })

        createHorizontalChain(packedA, packedB, packedC,
            chainStyle = ChainStyle.Packed)

        ChainItem(itemText = "A",
            modifier = Modifier.constrainAs(packedA) {
                top.linkTo(packedTitle.bottom)
            })

        ChainItem(itemText = "B",
            modifier = Modifier.constrainAs(packedB) {
                top.linkTo(packedTitle.bottom)
            })

        ChainItem(itemText = "C",
            modifier = Modifier.constrainAs(packedC) {
                top.linkTo(packedTitle.bottom)
            })
    }
}

@Composable
fun ChainItem(itemText: String, modifier: Modifier) {
//    val textColor = if (itemText == "A")
//        Color.Yellow
//    else if (itemText == "B") {
//        Color.Blue
//    }
//    else {
//        Color.Green
//    }

    val textColor = when (itemText) {
        "A" -> Color.Yellow
        "B" -> Color.Blue
        else -> Color.Green
    }

    Text(text = itemText,
        modifier = modifier.size(80.dp)
            .background(textColor)
            .wrapContentSize(Alignment.Center))
}

@Composable
fun Exercise2(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val startGuideline = createGuidelineFromStart(16.dp)
        val endGuideline = createGuidelineFromEnd(16.dp)
        val topGuideline = createGuidelineFromTop(16.dp)
        val bottomGuideline = createGuidelineFromBottom(16.dp)

        val halfHorizontalGuideline = createGuidelineFromTop(0.5f)
        val halfVerticalGuideline = createGuidelineFromStart(0.5f)

        val (textA, textB, textC) = createRefs()
        Text(text = "A",
            modifier = Modifier.constrainAs(textA) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                start.linkTo(startGuideline)
                end.linkTo(halfVerticalGuideline)
                top.linkTo(topGuideline)
                bottom.linkTo(halfHorizontalGuideline)
            }.background(Color.Yellow)
                .wrapContentSize(Alignment.Center))


        Text(text = "B",
            modifier = Modifier.constrainAs(textB) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                start.linkTo(halfVerticalGuideline)
                end.linkTo(endGuideline)
                top.linkTo(topGuideline)
                bottom.linkTo(halfHorizontalGuideline)
            }.background(Color.Green)
                .wrapContentSize(Alignment.Center))

        Text(text = "C",
            modifier = Modifier.constrainAs(textC) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(halfHorizontalGuideline)
                bottom.linkTo(bottomGuideline)
            }.background(Color.Blue)
                .wrapContentSize(Alignment.Center))
    }
}

@Composable
fun GuidelineDemo(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val startGuideline = createGuidelineFromStart(16.dp)
        val topGuideline = createGuidelineFromTop(16.dp)

        val (appButton) = createRefs()

        Button(onClick = {},
            modifier = Modifier.constrainAs(appButton) {
                start.linkTo(startGuideline)
                top.linkTo(topGuideline)
        }) { Text("DEMO BUTTON") }
    }
}

@Composable
fun Exercise1(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (box1, box2, box3, box4) = createRefs()
        Column(modifier = modifier.border(width = 2.dp, color = Color.Black)
            .padding(8.dp)
            .size(60.dp)
            .constrainAs(box1) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
            }) {
            Text(text = "1",
                modifier = Modifier.fillMaxWidth()
                                   .wrapContentWidth(Alignment.CenterHorizontally))
            Text(text = "Item 1",
                modifier = Modifier.fillMaxWidth()
                                   .wrapContentWidth(Alignment.CenterHorizontally))
        }

        Column(modifier = modifier.border(width = 2.dp, color = Color.Black)
            .padding(8.dp)
            .size(60.dp)
            .constrainAs(box2) {
                start.linkTo(box1.end, 16.dp)
                top.linkTo(box1.top)
            }) {
            Text(text = "2",
                modifier = Modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
            Text(text = "Item 2",
                modifier = Modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
        }

        Column(modifier = modifier.border(width = 2.dp, color = Color.Black)
            .padding(8.dp)
            .size(60.dp)
            .constrainAs(box3) {
                start.linkTo(box2.end, 16.dp)
                top.linkTo(box2.top)
            }) {
            Text(text = "3",
                modifier = Modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
            Text(text = "Item 3",
                modifier = Modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
        }

        Column(modifier = modifier.border(width = 2.dp, color = Color.Black)
            .padding(8.dp)
            .size(60.dp)
            .constrainAs(box4) {
                start.linkTo(box3.end, 16.dp)
                top.linkTo(box3.top)
            }) {
            Text(text = "4",
                modifier = Modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
            Text(text = "Item 4",
                modifier = Modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun ConstraintDemo(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (appButton, appText) = createRefs()
        Button(onClick = {  },
            modifier = Modifier.constrainAs(appButton) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
            }) { Text("APP BUTTON") }
        Text(text = "APP TEXT",
            modifier = Modifier.constrainAs(appText) {
                top.linkTo(appButton.bottom, 8.dp)
                start.linkTo(appButton.start)
                end.linkTo(appButton.end)
            })
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
    Week9_2Theme {
        Greeting("Android")
    }
}