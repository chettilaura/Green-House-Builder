package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.screens.caveatSemiBold


/*QUESTA CARD POTREBBE NON ESSERE MAI USATA, IN CASO E' DA ELIMINARE*/
@Composable
fun TurnCard(colorTurn: String, colorResId: Int) {
    val color = colorResource(id = colorResId)

    Card(modifier = Modifier
        .width(100.dp)
        .height(80.dp)
        .padding(2.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Row() {
                Text(text = "IT'S ", fontFamily = caveatSemiBold, style = MaterialTheme.typography.body1)
                Text(text = colorTurn, fontFamily = caveatBold, style = MaterialTheme.typography.subtitle1, color=color)
            }
            Text(text = "TURN!", fontFamily = caveatSemiBold, style = MaterialTheme.typography.body1)
        }
    }
}