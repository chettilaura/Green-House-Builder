package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatSemiBold


/***
 * Generalized card with parameter bars used to show user progress in the game.
 *
 * @param co2Impact shows progress indicator for the CO2 bar
 * @param comfort shows progress indicator for the comfort bar
 * @param economy shows progress indicator for the economy bar
 *
 *
 */

@Composable
fun ParameterBars(co2Impact: Float, comfort: Float, economy: Float) {
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
        .fillMaxHeight(0.15f),
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
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
                        Text(text = "CO2 IMPACT",
                            fontFamily = caveatSemiBold,
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                )
                LinearProgressIndicator(
                    progress = co2Impact,
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
                        Text(text = "COMFORT",
                            fontFamily = caveatSemiBold,
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                )
                LinearProgressIndicator(
                    progress = comfort,
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
                        Text(text = "ECONOMY",
                            fontFamily = caveatSemiBold,
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                )
                LinearProgressIndicator(
                    progress = economy,
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