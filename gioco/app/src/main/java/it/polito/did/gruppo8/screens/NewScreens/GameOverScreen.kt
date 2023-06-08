package it.polito.did.gruppo8.screens.NewScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Player
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.ui.theme.DarkGreen
import it.polito.did.gruppo8.util.myComposable.MyButton
import it.polito.did.gruppo8.util.myComposable.MyPlayerData
import it.polito.did.gruppo8.util.myComposable.ParameterBars

@Composable
fun GameOverScreen(vm: GameViewModel, modifier: Modifier = Modifier) {

    //val players = listOf<Player>()
    val players by vm.players.observeAsState()

    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            GameOverTopBar(title = "GAME OVER ", colorId = colorResource(id = R.color.cal_poly_green))

            RankedPlayers(players = players!!.values.toList().take(3))

            Text(
                text = "OVERALL",
                fontFamily = caveatBold,
                color = DarkGreen,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )

            //mostrata classifica generale (come in dashboard)

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                MyPlayerData(players!!.values.toList()) //da passare i giocatori e relativi parametri a funzione originale

            }

            MyButton(
                title = "EXIT",
                description = "FINE DEL GIOCO",
                buttonHeight = 100,
            ){/*vm.onExitGameButtonPressed()*/ }

        }
    }
}

@Composable
fun GameOverTopBar(title: String, colorId: Color) {
    TopAppBar (
        modifier = Modifier.height(100.dp),
        title = {
            Text(title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h2)

        },
        backgroundColor = colorId,
        elevation = 40.dp
    )
}

@Composable
fun RankedPlayers(players: List<Player>) {
    Column(
        modifier = Modifier
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            players.forEach { player ->

                val placement = players.indexOf(player)

                Card(modifier = Modifier
//                  .width(120.dp)
//                  .height(80.dp)
                    .padding(10.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(15.dp),
                    border = BorderStroke(3.dp,
                        when(placement){
                            0 -> Color(0xFFDAA520)
                            1 -> Color(0xFFC0C0C0)
                            2 -> Color(0xFFCD7F32)
                            else -> {
                                Color.DarkGray
                            }
                        }
                    )
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.15f),
                        /*.padding(1.dp),*/
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        if(placement<=2) {
                            Image(
                                painter = painterResource(
                                    when(placement){
                                        0 -> R.drawable.coccarda1
                                        1 -> R.drawable.coccarda2
                                        2 -> R.drawable.coccarda3
                                        else -> {
                                            0
                                        }
                                    }
                                ),
                                contentDescription = "Award",
                                //modifier = Modifier.size(50.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        Text(
                            text = player.nickname,
                            fontFamily = caveatBold,
                            color = DarkGreen,
                            style = MaterialTheme.typography.h3,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = player.house.stats.weightedAverage().toString(),
                            fontFamily = caveatBold,
                            color = DarkGreen,
                            style = MaterialTheme.typography.h3,
                            textAlign = TextAlign.Center
                        )
                        //ParameterBars(player.house.stats, 0.1f)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameOverScreenPreview(modifier: Modifier = Modifier) {
    GameOverScreen(GameViewModel())
}