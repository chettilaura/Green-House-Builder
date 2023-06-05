package it.polito.did.gruppo8.util.myComposable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.model.baseClasses.Player
import it.polito.did.gruppo8.model.baseClasses.Statistics
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.ui.theme.DarkGreen


//composable legata alla lista dei giocatori
//legata a dashboard dell'host e a game over

@Composable
fun MyPlayerData(players: List<Player>) {

//    Box(modifier = Modifier.fillMaxSize(1f)) {
//        Image(
//            painter = painterResource(R.drawable.bg),
//            contentDescription = "Immagine di sfondo",
//            modifier = Modifier.fillMaxHeight(),
//            contentScale = ContentScale.FillHeight
//        )

        Column(
            modifier = Modifier
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            Text(
//                text = "GAME OVER",
//                fontFamily = caveatBold,
//                color = Color.Black,
//                style = MaterialTheme.typography.h2,
//                textAlign = TextAlign.Center
//            )

            //da popolare con lista di giocatori

                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .verticalScroll(rememberScrollState())
                        .weight(1f, false),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    players.forEach { player ->
                        Text(
                            text = player.nickname,
                            fontFamily = caveatBold,
                            color = DarkGreen,
                            style = MaterialTheme.typography.h3,
                            textAlign = TextAlign.Center
                        )
                        ParameterBars(player.house.stats, 0.1f)
                    }

                    /*
                    Text(
                        text = "Player1",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Text(
                        text = "Player2",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Text(
                        text = "Player3",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Text(
                        text = "Player4",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Text(
                        text = "Player5",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Text(
                        text = "Player6",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Text(
                        text = "Player7",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Text(
                        text = "Player8",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Text(
                        text = "Player9",
                        fontFamily = caveatBold,
                        color = DarkGreen,
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                    ParameterBars(Statistics(), 0.1f)
                    Spacer(modifier = Modifier.size(25.dp))
                     */
                }
            MyButton(
                title = "END GAME",
                description = "FINE DEL GIOCO",
                buttonHeight = 100,
                ){/*vm.onExitGameButtonPressed()*/ }

                //cliccando il button la partita termina
            }
        }
   // }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewPlayerList(){
    MyPlayerData(listOf())
}