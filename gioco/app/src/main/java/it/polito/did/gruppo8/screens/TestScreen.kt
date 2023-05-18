package it.polito.did.gruppo8.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R

/*TODO: Schermata di test per provare elementi grafici*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestScreen() {
    val houses = remember { mutableStateListOf<String>("Alice", "Bob", "Charlie", "David", "Emma",
            "Frank", "Grace", "Henry", "Ivy", "Jack",
            "Kate", "Liam", "Mia", "Noah", "Olivia")
    }

    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumnListCard(houses)
            Button(onClick = { houses.add(0,"newname") }, ) {
                Text(text = "ADD")
            }
        }
    }
}

@Composable
fun LazyColumnListCard(list: MutableList<String>) {
    Card(modifier = Modifier
        .fillMaxWidth(0.6f)
        .fillMaxHeight(0.05f),
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.Black),
        backgroundColor = colorResource(id = R.color.cal_poly_green)
    )
    {
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(list){ item ->
                Text(
                    modifier = Modifier.animateContentSize (),
                    text = "HOUSE $item",
                    fontFamily = caveatBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestScreenPreview() {
    TestScreen()
}