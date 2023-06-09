package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Statistics
import it.polito.did.gruppo8.screens.caveatSemiBold


/***
 * Generalized card with parameter bars used to show user progress in the game.
 *
 * @param stats Statistics of the house
 * @param fraction indicates the relative height of the card in the screen
 */
@Composable
fun ParameterBars(stats: Statistics, fraction: Float) {
   /* val co2Impact = rememberSaveable {
        mutableStateOf (0.7f)
    }
    val comfort = rememberSaveable {
        mutableStateOf (0.4f)
    }
    val economy = rememberSaveable {
        mutableStateOf (0.5f)
    }*/

    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(fraction),
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(  //Parametro CO2
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Row(  //Parametro CO2
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(painter = painterResource(R.drawable.leaf),
                            contentDescription = "leaf")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "GREEN",
                            fontFamily = caveatSemiBold,
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(
                    modifier = Modifier
                        .weight(0.2f)
                )
                LinearProgressIndicator(
                    progress = stats.green.toFloat()/Statistics.MAX_GREEN,
                    color = colorResource(id = R.color.kelly_green),
                    backgroundColor = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .size(10.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Row( //Parametro COMFORT
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.4f)){
                    Row(  //Parametro CO2
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(painter = painterResource(R.drawable.sofa),
                            contentDescription = "sofa")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "COMFY",
                            fontFamily = caveatSemiBold,
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(
                    modifier = Modifier
                        .weight(0.2f)
                )
                LinearProgressIndicator(
                    progress = stats.comfy.toFloat()/Statistics.MAX_COMFY,
                    color = colorResource(id = R.color.glaucous),
                    backgroundColor = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(10.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
            }

            Row(  //Parametro ECONOMY
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Row(  //Parametro CO2
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(painter = painterResource(R.drawable.coin),
                            contentDescription = "coin")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "LOWCOST",
                            fontFamily = caveatSemiBold,
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(
                    modifier = Modifier
                        .weight(0.2f)
                )
                LinearProgressIndicator(
                    progress = stats.lowcost.toFloat()/Statistics.MAX_LOWCOST,
                    color = colorResource(id = R.color.xanthous),
                    backgroundColor = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(10.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
            }
        }
    }
}


@Preview (showBackground = true, showSystemUi = true)
@Composable
fun MyParameterBarCardPreview() {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        val stats = Statistics()
        Spacer(modifier = Modifier.height(10.dp))
        ParameterBars(stats, fraction = 0.15f)
        Spacer(modifier = Modifier.height(10.dp))
        ParameterBars(stats, fraction = 1f)
        Spacer(modifier = Modifier.height(10.dp))
    }
}
