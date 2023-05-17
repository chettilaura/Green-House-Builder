package it.polito.did.gruppo8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.util.myComposable.MoneyCard
import it.polito.did.gruppo8.util.myComposable.MyButton
import it.polito.did.gruppo8.util.myComposable.ParameterBars
import it.polito.did.gruppo8.util.myComposable.RoundCard
import it.polito.did.gruppo8.util.myComposable.MyTimerCard

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
            ParameterBars(0.7f, 0.4f, 0.5f, 0.15f)
            Spacer(modifier = Modifier
                .weight(1f))

            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f),
                /*.padding(1.dp),*/
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                RoundCard(numeroTurno.value)
                Spacer(modifier = Modifier.weight(1f))

                MyTimerCard(30_000)
                Spacer(modifier = Modifier.weight(1f))

                MoneyCard(money.value)
            }
            Spacer(modifier = Modifier
                .weight(0.05f))

            HouseViewBox(/*TODO: passare parametri degli elementi da mostrare della casa*/)
            Spacer(modifier = Modifier
                .weight(0.05f))

            Column(modifier = Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                MyButton(title = "VIEW ITEM LIST", description = "View item list button") {
                    /*TODO: passare funzione di navigazione*/
                }
                Spacer(modifier = Modifier
                    .weight(0.05f))

                MyButton(title = "SHOP", description = "Shop button") {
                    /*TODO: passare funzione di navigazione*/
                }
                Spacer(modifier = Modifier
                    .weight(0.05f))

                MyButton(title = "LEADERBOARD", description = "Leaderboard button") {
                    /*TODO: passare funzione di navigazione*/
                }
            }
        }
    }
}

@Composable
fun HouseViewBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f)
    ) {
        Image(painter = painterResource(R.drawable.house),
            contentDescription = "house",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HouseOverviewScreenPreview() {
    HouseOverviewScreen()
}