package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.screens.caveatRegular

@Composable
fun RoundCard(roundNum : String) {
    Card(modifier = Modifier
        .width(120.dp)
        .height(80.dp)
        .padding(2.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "ROUND", fontFamily = caveatRegular, style = MaterialTheme.typography.body1)
            Text(text = roundNum, fontFamily = caveatBold, style = MaterialTheme.typography.h6)
        }
    }
}