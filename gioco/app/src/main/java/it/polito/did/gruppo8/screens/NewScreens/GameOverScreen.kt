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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Player
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.screens.caveatSemiBold
import it.polito.did.gruppo8.util.myComposable.MyButton
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameOverTopBar(title = "GAME OVER ", colorId = colorResource(id = R.color.cal_poly_green))

            LazyColumn(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(players!!.values.toList()) { index, player ->
                    PlayerRankingCard(player, index)
                }
            }
            /*RankedPlayers(players = players!!.values.toList())*/

            MyButton(
                title = "EXIT",
                description = "FINE DEL GIOCO",
                buttonHeight = 100,
            ){/*vm.onExitGameButtonPressed() */}
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
    LazyColumn(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(players) { index, player ->
            PlayerRankingCard(player, index)
        }
    }
}

@Composable
fun PlayerRankingCard(player: Player, index:Int) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .wrapContentHeight(),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            2.dp,
            when (index) {
                0 -> Color(0xFFDAA520)
                1 -> Color(0xFFC0C0C0)
                2 -> Color(0xFFCD7F32)
                else -> Color.Black
            })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(
                            when (index) {
                                0 -> R.drawable.coccarda1
                                1 -> R.drawable.coccarda2
                                2 -> R.drawable.coccarda3
                                else -> 0
                            }),
                        contentDescription = "Award",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = player.nickname + " ",
                        fontFamily = caveatBold,
                        color = Color.Black,
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Start
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "SCORE: ",
                        fontFamily = caveatSemiBold,
                        color = Color.Black,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = player.house.stats.weightedAverage().toString(), /*TODO: controllare che sia punteggio corretto*/
                        fontFamily = caveatBold,
                        color = Color.Black,
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Start
                    )
                }
                ParameterBars(player.house.stats, 0.2f)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameOverScreenPreview(modifier: Modifier = Modifier) {
    GameOverScreen(GameViewModel())
}