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
import androidx.compose.runtime.saveable.rememberSaveable
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
import it.polito.did.gruppo8.util.myComposable.ParameterBars

import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

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
    val colorTurn = rememberSaveable {
        mutableStateOf ("RED")
    }
    val colorResId = rememberSaveable {
        mutableStateOf (old_rose)
    }
    val numeroTurno = rememberSaveable{
        mutableStateOf("1/8")
    }
    val money = rememberSaveable{
        mutableStateOf (534)
    }

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
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ParameterBars(0.7f, 0.4f, 0.5f)
            Spacer(modifier = Modifier
                .size(2.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                /*TurnCard(
                    colorTurn.value, colorResId = old_rose
                    *//*TODO: passare colore della squadra con turno attivo
                    *  il colore è in formato Int e il nome è contenuto nel file colors.xml dentro res,
                    *  il nome deve essere corrispondente al colore *//*
                )
                Spacer(modifier = Modifier.weight(1f))*/

                RoundCard(numeroTurno.value)
                Spacer(modifier = Modifier.weight(1f))

                TimerCard()
                Spacer(modifier = Modifier.weight(1f))

                MoneyCard(money.value)
            }
            Spacer(modifier = Modifier
                .size(2.dp))

            HouseViewBox(/*TODO: passare parametri degli elementi da mostrare della casa*/)
            Spacer(modifier = Modifier
                .size(2.dp))

            OutlinedButton(onClick = { /*TODO: vai a lista di oggetti acquistati onViewItemList*/ },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, Color.Black),
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

            OutlinedButton(onClick = { /*TODO: vai a vista del quartiere*/ },
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = emerald)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(
                    text = "DISTRICT VIEW",
                    fontFamily = caveatBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h5
                )
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
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Black)) {
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
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = fact,
                        fontFamily = caveatRegular,
                        color=Color.White,
                        style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}

@Composable
fun MoneyCard(money : Int) {

    Card(modifier = Modifier
        .width(120.dp)
        .height(80.dp)
        .padding(2.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.DarkGray)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
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
                Text(text = "$money", fontFamily = caveatBold, style = MaterialTheme.typography.h6)
            }
        }
    }
}

@Composable
fun RoundCard(roundNum : String) {
    Card(modifier = Modifier
        .width(120.dp)
        .height(80.dp)
        .padding(2.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
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
        .height(80.dp)
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
//funzione per il timer
/*TODO: impostare inizio del timer e cambio di schermata allo scadere del timer*/
fun rememberCountdownTimerState(
    initialMillis: Long,
    step: Long = 1000
): MutableState<Long> {
    val timeLeft = remember {mutableStateOf(initialMillis)}
    LaunchedEffect(initialMillis, step) {
        while (isActive && timeLeft.value > 0) {
            timeLeft.value = (timeLeft.value - step).coerceAtLeast(0)
            delay(step)
        }
    }
    return timeLeft
}

@Composable
fun TimerCard() {
    //valore del tempo rimanente
    val timeLeftMs by rememberCountdownTimerState (30_000) //tempo in ms
    val showTime = timeLeftMs / 1000 //tempo in secondi

    Card(modifier = Modifier
        .width(100.dp)
        .height(80.dp)
        .padding(2.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
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
            Text(text = "$showTime" + " sec", fontFamily = caveatBold, style = MaterialTheme.typography.h6)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HouseOverviewScreenPreview() {
    HouseOverviewScreen()
}