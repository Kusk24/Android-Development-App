package com.example.csx4109_542_assignment_2_6612054.View

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.example.csx4109_542_assignment_2_6612054.Model.CharacterResult

@Composable
fun DetailScreen(result: CharacterResult) {

    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val (imageC, nameC, statusC, speciesC) = createRefs()


        AsyncImage(
            result.image, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .constrainAs(imageC) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text("Name: ${result.name}", modifier = Modifier.constrainAs(nameC) {
            top.linkTo(imageC.bottom, 8.dp)
            start.linkTo(parent.start)
        })

        Text("Status: ${result.status}", modifier = Modifier.constrainAs(statusC) {
            top.linkTo(nameC.bottom, 5.dp)
            start.linkTo(parent.start)
        })

        Text("Species: ${result.species}", modifier = Modifier.constrainAs(speciesC) {
            top.linkTo(statusC.bottom, 5.dp)
            start.linkTo(parent.start)
        })

    }
}