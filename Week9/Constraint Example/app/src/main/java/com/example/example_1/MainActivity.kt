package com.example.example_1

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
import com.example.example_1.ui.theme.Example_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Example_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    ConstraintDemo(Modifier.padding(innerPadding))
//                    Exercise2_Guideline(Modifier.padding(innerPadding))
                    Exercise3_ChainSpread(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConstraintDemo(modifier: Modifier){
    ConstraintLayout(modifier = modifier.fillMaxSize()){
        val(appButton, appText) = createRefs()
        Button(onClick = { },
            modifier = Modifier.constrainAs(appButton) {
                top.linkTo(parent.top,16.dp)
                start.linkTo(parent.start, 16.dp)
            }) { Text(text = "APP BUTTON")}

            Text("APP TEXT",
                modifier = Modifier.constrainAs(appText) {
                    top.linkTo(appButton.bottom, 16.dp)
                    start.linkTo(appButton.start, 16.dp)
                })

    }
}

@Composable
fun Exercise1_Constraint_my(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val(item1, item2, item3, item4) = createRefs()
        Text("  1  \n Item 1",
            modifier = Modifier.constrainAs(item1){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
            })

        Text("  2  \n Item 2",
            modifier = Modifier.constrainAs(item2){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(item1.start, 100.dp)
            })

        Text("  3  \n Item 3",
            modifier = Modifier.constrainAs(item3){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(item2.start, 100.dp)
            })

        Text("  4  \n Item 4",
            modifier = Modifier.constrainAs(item4){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(item3.start, 100.dp)
            })
    }
}

@Composable
fun Exercise1_Constraint(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val(item1, item2, item3, item4) = createRefs()

        Column(modifier = Modifier.border(width = 2.dp, color = Color.Black)
            .size(80.dp)
            .padding(8.dp)
            .constrainAs(item1){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
            }){
            Text("1", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
            Text("Item 1", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
        }

        Column( modifier = Modifier.border(width = 2.dp, color = Color.Black)
            .size(80.dp)
            .padding(8.dp)
            .constrainAs(item2){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(item1.end, 16.dp)
            }){
            Text("2", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
            Text("Item 2", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
        }

        Column( modifier = Modifier.border(width = 2.dp, color = Color.Black)
            .size(80.dp)
            .padding(8.dp)
            .constrainAs(item3){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(item2.end, 16.dp)
            }){
            Text("3", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
            Text("Item 3", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
        }

        Column(modifier = Modifier.border(width = 2.dp, color = Color.Black)
            .size(80.dp)
            .padding(8.dp)
            .constrainAs(item4){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(item3.end, 16.dp)
            }){
            Text("4", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
            Text("Item 4", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun GuidelineDemo(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val startGuideline = createGuidelineFromStart(16.dp)
        val topGuideline = createGuidelineFromTop(16.dp)

        val (appButton) = createRefs()

        Button(onClick = {},
            modifier = Modifier.constrainAs(appButton){
                start.linkTo(startGuideline)
                top.linkTo(topGuideline)
            }){ Text("DEMO BUTTON")}

    }
}
@Composable
fun Exercise2_Guideline(modifier: Modifier){
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val halfVerticalLine = createGuidelineFromStart(0.5f)
        val halfHorizonalLine = createGuidelineFromTop(0.5f)

        val (A,B,C) = createRefs()

        Text("A",
            modifier = Modifier
                .background(Color.Yellow)
                .wrapContentSize(Alignment.Center)
                .constrainAs(A){
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                start.linkTo(parent.start, 16.dp)
                end.linkTo(halfVerticalLine)
                bottom.linkTo(halfHorizonalLine)
                top.linkTo(parent.top, 16.dp)
            })

        Text("B",
            modifier = Modifier
                .background(Color.Green)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .constrainAs(B){
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                start.linkTo(halfVerticalLine)
                end.linkTo(parent.end, 16.dp)
                bottom.linkTo(halfHorizonalLine)
                top.linkTo(parent.top, 16.dp)
        })

        Text("C",
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .constrainAs(C){
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end, 16.dp)
                top.linkTo(halfHorizonalLine)
        })
    }
}

@Composable
fun Exercise3_ChainSpread(modifier : Modifier){

    ConstraintLayout ( modifier = modifier.fillMaxSize() ){
        val (spreadTitle, spreadA, spreadB, spreadC,
            spreadInsideTitile, spreadInsideA, spreadInsideB, spreadInsideC,
            weightedTitle, weightedA, weightedB, weightedC,
            packedTitle, packedA, packedB, packedC) = createRefs()


        //Spread
        Text("Spread",
            modifier = Modifier.constrainAs(spreadTitle) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
            })

        createHorizontalChain(spreadA,spreadB,spreadC, chainStyle = ChainStyle.Spread)

        Text("A" , modifier = Modifier.constrainAs(spreadA) {
            top.linkTo(spreadTitle.bottom, 16.dp)
        }.size(80.dp).background(Color.Yellow).wrapContentSize(Alignment.Center))

        Text("B" , modifier = Modifier.constrainAs(spreadB) {
            top.linkTo(spreadTitle.bottom, 16.dp)
        }.size(80.dp).background(Color.Blue).wrapContentSize(Alignment.Center))

        Text("C" , modifier = Modifier.constrainAs(spreadC) {
            top.linkTo(spreadTitle.bottom, 16.dp)
        }.size(80.dp).background(Color.Green).wrapContentSize(Alignment.Center))


        //SpreadInside

        Text(text = "SpreadInside", modifier = modifier.constrainAs(spreadInsideTitile){
                top.linkTo(spreadA.bottom, 16.dp)
        })

        createHorizontalChain(spreadInsideA,spreadInsideB,spreadInsideC, chainStyle = ChainStyle.SpreadInside)

        ChainItem("A", Modifier.constrainAs(spreadInsideA){
            top.linkTo(spreadInsideTitile.bottom, 16.dp)
        })

        ChainItem("B", Modifier.constrainAs(spreadInsideB){
            top.linkTo(spreadInsideTitile.bottom, 16.dp)
        })

        ChainItem("C", Modifier.constrainAs(spreadInsideC){
            top.linkTo(spreadInsideTitile.bottom, 16.dp)
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

        createHorizontalChain(spreadInsideA,spreadInsideB,spreadInsideC, chainStyle = ChainStyle.SpreadInside)


    }
}

@Composable
fun ChainItem(itemText: String, modifier: Modifier) {
    val textColor = when (itemText) {
        "A" -> Color.Yellow
        "B" -> Color.Blue
        else -> Color.Green
    }
    Text(text = itemText, modifier = modifier.size(80.dp)
        .background(textColor)
        .wrapContentSize(Alignment.Center))
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
    Example_1Theme {
        Greeting("Android")
    }
}