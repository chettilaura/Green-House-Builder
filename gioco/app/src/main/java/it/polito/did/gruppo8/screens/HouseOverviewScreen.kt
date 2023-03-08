package it.polito.did.gruppo8.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.R.color.*

//Contiene vista generale della propria casa. Contiene: box con info sui parametri (barre),
//box con casetta, timer, soldi e info sul round di gioco
//pulsante che porta alla lista degli oggetti
//box con info sulla stagione nel gioco e fun fact (da confermare)
//possibilità di swipe a sinistra sulla pagina per osservare lista delle case della città:
//      contiene box con info sui parametri del giocatore, timer per il tempo del turno, lista degli altri giocatori, con nomi e barre per parametri
//      pulsanti per mostrare solo i giocatori di un determinato quartiere
//      pulsante di back/possibilità di swipare a destra per tornare alla vista precedente
//NOTA: lo sfondo è dello stesso colore del quartiere assegnato al giocatore

/*val MyFontFamily = FontFamily(Font(R.font.caveat_regular))*/
/*val LocalFont = staticCompositionLocalOf<FontFamily?> { null }*/
val caveatRegular = FontFamily(
    Font(R.font.caveat_regular, FontWeight.Normal, FontStyle.Normal)
)
val caveatMedium = FontFamily(
    Font(R.font.caveat_medium, FontWeight.Medium, FontStyle.Normal)
)
val caveatSemiBold = FontFamily(
    Font(R.font.caveat_semibold, FontWeight.SemiBold, FontStyle.Normal)
)
val caveatBold = FontFamily(
    Font(R.font.caveat_bold, FontWeight.Bold, FontStyle.Normal)
)
/*val sunnyWeather = FontFamily(
    Font(R.font.sunnyweather, FontWeight.Normal, FontStyle.Normal)
)*/

@Composable

fun HouseOverviewScreen(modifier: Modifier = Modifier)
{

    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg_green),
            contentDescription = "Immagine di sfondo verde",
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
                TurnCard(
                    colorTurn = "RED", colorResId = old_rose
                    /*TODO: passare parametro della squadra con turno attivo
                    *  il colore è in formato Int e il nome è contenuto nel file colors.xml dentro res,
                    *  il nome deve essere corrispondente al colore */
                )
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
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = emerald)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(text = "VIEW ITEM LIST",  fontFamily = caveatBold, color = Color.White, style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = Modifier
                .size(4.dp))

            SeasonCard("WINTER", "RANDOM FACT"/*TODO: passare la stagione e il testo da inserire*/)
            Spacer(modifier = Modifier
                .size(4.dp))

            Button(onClick = { /*TODO: vai a vista del quartiere*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = emerald)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(text = "DISTRICT VIEW",  fontFamily = caveatBold, color = Color.White, style = MaterialTheme.typography.h6)
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
            /*.border(BorderStroke(2.dp, Color.DarkGray))*/
    ) {
        Image(painter = painterResource(R.drawable.house),
            contentDescription = "house",
            modifier = Modifier.fillMaxSize(1f),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun SeasonCard(season: String, fact: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.6f)
        .padding(2.dp),
        backgroundColor = colorResource(id = emerald),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "IT'S " + season + "!", fontFamily = caveatSemiBold, color = Color.White,style = MaterialTheme.typography.h3)
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
                backgroundColor = colorResource(id = asparagus),
                elevation = (2).dp,
                border = BorderStroke(2.dp, Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = fact, fontFamily = caveatRegular, color=Color.White, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}

@Composable
fun MoneyCard(money : String) {
    Card(modifier = Modifier
        .width(100.dp)
        .height(70.dp)
        .padding(2.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(painter = painterResource(id = R.drawable.coin),
                contentDescription = "coin",
                modifier = Modifier.fillMaxHeight(0.6f),
                contentScale = ContentScale.FillHeight)
            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.End
            ) {
               /* Text(text = "MONEY", fontFamily = caveatRegular, style = MaterialTheme.typography.body1)*/
                Text(text = money, fontFamily = caveatBold, style = MaterialTheme.typography.h6)
            }
        }
    }
}

@Composable
fun RoundCard(roundNum : String) {
    Card(modifier = Modifier
        .width(80.dp)
        .height(80.dp)
        .padding(2.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)) {
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

@Composable
fun TurnCard(colorTurn: String, colorResId: Int) {
    val color = colorResource(id = colorResId)

    Card(modifier = Modifier
        .width(100.dp)
        .height(70.dp)
        .padding(2.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.DarkGray)) {
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
        /*Text(text = "It's " + colorTurn , style = MaterialTheme.typography.body1)*/
    }
}

@Composable
fun TimerCard() {
    Card(modifier = Modifier
        .width(80.dp)
        .height(80.dp)
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
            Image(painter = painterResource(id = R.drawable.clock),
                contentDescription = "clock",
                modifier = Modifier.weight(0.8f),
                contentScale = ContentScale.FillHeight
            )
            /*Text(text = "TIMER", fontFamily = caveatRegular, style = MaterialTheme.typography.body1)*/
            Text(text = "00:34", fontFamily = caveatBold, style = MaterialTheme.typography.h6)
        }
    }
}

@Composable
fun ParameterBars() {
    /*val customFont = FontFamily(
        Font(R.font.sunnyweather, FontWeight.Normal, FontStyle.Normal)
    )*/

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
                    progress = 0.7f, //TODO: passare qui valore della lunghezza della barra
                    color = colorResource(id = kelly_green),
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
                //TODO: aggiungere icona
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
                    progress = 0.4f, //TODO: passare qui valore della lunghezza della barra
                    color = colorResource(id = glaucous),
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
                //TODO: aggiungere icona
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
                    progress = 0.5f,  //TODO: passare qui valore della lunghezza della barra
                    color = colorResource(id = xanthous),
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HouseOverviewScreenPreview() {
    HouseOverviewScreen()
}