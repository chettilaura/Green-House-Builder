package it.polito.did.gruppo8.screens

import android.widget.ProgressBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.*

//Contiene vista generale della propria casa. Contiene: box con info sui parametri (barre),
//box con casetta, timer, soldi e info sul round di gioco
//pulsante che porta alla lista degli oggetti
//box con info sulla stagione nel gioco e fun fact (da confermare)
//possibilità di swipe a sinistra sulla pagina per osservare lista delle case della città:
//      contiene box con info sui parametri del giocatore, timer per il tempo del turno, lista degli altri giocatori, con nomi e barre per parametri
//      pulsanti per mostrare solo i giocatori di un determinato quartiere
//      pulsante di back/possibilità di swipare a destra per tornare alla vista precedente
//NOTA: lo sfondo è dello stesso colore del quartiere assegnato al giocatore

@Composable
fun HouseOverviewScreen(modifier: Modifier = Modifier)
{
    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ParameterBars(/*TODO: passare parametri per definire lunghezza delle barre*/)
            Spacer(modifier = Modifier
                .size(2.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                TurnCard(colorTurn = "RED", color = Color.Red /*TODO: passare parametro della squadra con turno attivo*/)
                Spacer(modifier = Modifier.weight(1f))
                RoundCard("1/8"/*TODO: passare parametro del numero di turno*/)
                Spacer(modifier = Modifier.weight(1f))
                TimerCard(/*TODO: passare parametro del tempo rimanente per la fine del turno*/)
                Spacer(modifier = Modifier.weight(1f))
                MoneyCard("534"/*TODO: passare valore dei soldi*/)
            }
            Spacer(modifier = Modifier
                .size(2.dp))

            HouseViewBox(/*TODO: passare parametri degli elementi da mostrare della casa*/)
            Spacer(modifier = Modifier
                .size(2.dp))

            Button(onClick = { /*TODO: vai a lista di oggetti acquistati*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(text = "View Item List")
            }
            Spacer(modifier = Modifier
                .size(4.dp))

            SeasonCard("Winter", "Random fact"/*TODO: passare la stagione e il testo da inserire*/)
            Spacer(modifier = Modifier
                .size(4.dp))

            Button(onClick = { /*TODO: vai a vista del quartiere*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(text = "District View")
            }

        }

    }
}

@Composable
fun HouseViewBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(2.dp)
            .border(BorderStroke(2.dp, Color.DarkGray))
    ) {

    }
}

@Composable
fun SeasonCard(season: String, fact: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.6f)
        .padding(2.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "It's " + season + "!", style = MaterialTheme.typography.h3)
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
                elevation = (2).dp,
                border = BorderStroke(2.dp, Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = fact, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}

@Composable
fun MoneyCard(money : String) {
    Card(modifier = Modifier
        .width(80.dp)
        .height(60.dp)
        .padding(2.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "Money", style = MaterialTheme.typography.body1)
            Text(text = money, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun RoundCard(roundNum : String) {
    Card(modifier = Modifier
        .width(80.dp)
        .height(60.dp)
        .padding(2.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Round", style = MaterialTheme.typography.body1)
            Text(text = roundNum, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun TurnCard(colorTurn : String, color : Color) {
    Card(modifier = Modifier
        .width(80.dp)
        .height(60.dp)
        .padding(2.dp),
        backgroundColor = color,
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "It's " + colorTurn , style = MaterialTheme.typography.body1)
            Text(text = "turn!", style = MaterialTheme.typography.body2)
        }
    }

}

@Composable
fun TimerCard() {
    Card(modifier = Modifier
        .width(80.dp)
        .height(60.dp)
        .padding(4.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Timer", style = MaterialTheme.typography.body1)
            Text(text = "00:34", style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun ParameterBars() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.15f)
        .padding(4.dp),
        elevation = 4.dp,
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
                //TODO: aggiungere icona
                Box(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Text(text = "CO2 Impact",
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                )
                LinearProgressIndicator(
                    progress = 0.7f, //TODO: passare qui valore della lunghezza della barra
                    color = Color.Black,
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
                //TODO: aggiungere icona
                Box(modifier = Modifier.fillMaxWidth(0.4f)){
                    Text(text = "Comfort",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Left
                    )
                }
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                )
                LinearProgressIndicator(
                    progress = 0.4f, //TODO: passare qui valore della lunghezza della barra
                    color = Color.Black,
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
                //TODO: aggiungere icona
                Box(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Text(text = "Economy",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Left
                    )
                }
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                )
                LinearProgressIndicator(
                    progress = 0.5f,  //TODO: passare qui valore della lunghezza della barra
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(10.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HouseOverviewScreenPreview() {
    HouseOverviewScreen()
}