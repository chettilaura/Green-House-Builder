package it.polito.did.gruppo8.screens.NewScreens
import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.util.myComposable.*
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewParameter
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.Navigator
import it.polito.did.gruppo8.ScreenName
import it.polito.did.gruppo8.screens.caveatBold
import kotlinx.coroutines.delay


@Composable
fun WaitingQuizScreen(vm: GameViewModel, modifier: Modifier = Modifier) {
    val players by vm.players.observeAsState()
    val gameInfos by vm.gameInfos.observeAsState()

    Log.d("WaitingQuizScreen", "CurrentPlayerID: ${gameInfos!!.currentPlayerId}")
    Log.d("WaitingQuizScreen", "CurrentPlayer: ${players!![gameInfos!!.currentPlayerId]}")

    //Genera il prossimo quiz
    vm.prepareNextQuiz()

    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column() {

            // Seleziona il nickname del player corrente (scrive "Your" se è uguale al proprio)
            val nickname = if(gameInfos!!.currentPlayerId != players!![vm.myPlayerId]?.id)
                players!![gameInfos!!.currentPlayerId]?.nickname?:""
            else
                "YOUR"
            QuizTopBar(
                title = "IT'S $nickname TURN",
                colorId = colorResource(id = R.color.cal_poly_green)
            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingAnimation()
                    Spacer(modifier = Modifier.size(40.dp))

                        Box(
                            modifier = Modifier
                                .size(400.dp, 400.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color(0xFF599B68))
                        ) {

                            Column() {
                                Spacer(modifier = Modifier.size(10.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "IF IT'S YOU...",
                                        fontFamily = caveatBold,
                                        color = Color.White,
                                        style = MaterialTheme.typography.h4
                                    )
                                }

                                Box(modifier = Modifier) {

                                    Column(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "RIGHT ANSWER",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )
                                            Spacer(modifier = Modifier.size(40.dp))

                                            // TODO:inserire valore delle monete

                                            Image(
                                                painter = painterResource(id = R.drawable.coin),
                                                contentDescription = "houseIcon" )
                                        }

                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "WRONG ANSWER",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )
                                            Spacer(modifier = Modifier.size(40.dp))

                                            // TODO:inserire valore delle monete

                                            Image(
                                                painter = painterResource(id = R.drawable.coin),
                                                contentDescription = "coinIcon" )
                                        }

                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "NO ANSWER",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )
                                            Spacer(modifier = Modifier.size(40.dp))

                                            // TODO:inserire valore delle monete

                                            Image(
                                                painter = painterResource(id = R.drawable.coin),
                                                contentDescription = "coinIcon"
                                            )
                                        }

                                    }
                                }

                                Spacer(modifier = Modifier.size(10.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "IF IT'S NOT YOU...",
                                        fontFamily = caveatBold,
                                        color = Color.White,
                                        style = MaterialTheme.typography.h4
                                    )
                                }

                                Box(modifier = Modifier) {

                                    Column(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "RIGHT ANSWER",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )
                                            Spacer(modifier = Modifier.size(40.dp))

                                            // TODO:inserire valore delle monete

                                            Image(
                                                modifier = Modifier.size(50.dp,50.dp),
                                                painter = painterResource(id = R.drawable.house),
                                                contentDescription = "houseIcon")
                                        }

                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "WRONG ANSWER",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )
                                            Spacer(modifier = Modifier.size(40.dp))

                                            // TODO:inserire valore delle monete

                                            Image(
                                                modifier = Modifier.size(50.dp,50.dp),
                                                painter = painterResource(id = R.drawable.noshop),
                                                contentDescription = "houseIcon")

                                        }

                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "NO ANSWER",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )
                                            Spacer(modifier = Modifier.size(40.dp))

                                            // TODO:inserire valore delle monete

                                            Image(
                                                modifier = Modifier.size(50.dp,50.dp),
                                                //TODO: Manca resource noshop nella cartella -Mattia
                                                painter = painterResource(id = R.drawable.noshop),
                                                contentDescription = "houseIcon")

                                        }
                                    }
                                }
                            }
                        }
                }
            }
        }
    }
}



// COMPOSABLES

@Composable
fun QuizTopBar(title: String, colorId: Color) {
    TopAppBar (
        modifier = Modifier.height(100.dp),
        title = {
            Text(title,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h3)
        },
        backgroundColor = colorId,
        elevation = 40.dp
    )
}


/* PREVIEW ---------------------------------------------------- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWaitingQuizScreen(modifier: Modifier = Modifier) {
    //val vm = GameViewModel()
    GameSkeletonTheme {
        WaitingQuizScreen(GameViewModel())
    }
}


