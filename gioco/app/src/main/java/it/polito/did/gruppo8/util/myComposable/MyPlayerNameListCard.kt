package it.polito.did.gruppo8.util.myComposable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Player
import it.polito.did.gruppo8.screens.caveatBold

/***
 * Generalized card with a header and a list of strings shown in a scrollable list of cards
 *
 *
 * @param header text showing the name of object contained in the list
 * @param list list of strings to show in the scrollable cards
 */
@Composable
fun MyPlayerNameListCard(header: String, playersList: List<Player>) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.15f)
        .padding(2.dp),
        backgroundColor = colorResource(id = R.color.emerald),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = header,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.width(16.dp))

            Box(modifier = Modifier.fillMaxSize()
            ){
                LazyColumn(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    items(playersList){ player ->
                        Card(modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(2.dp),
                            backgroundColor = colorResource(id = R.color.cal_poly_green),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(2.dp, Color.Black)
                        ) {
                            Text(
                                modifier = Modifier.animateContentSize(),
                                text = player.nickname,
                                fontFamily = caveatBold,
                                color = Color.White,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

