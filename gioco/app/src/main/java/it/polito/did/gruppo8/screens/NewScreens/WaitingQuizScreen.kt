package it.polito.did.gruppo8.screens.NewScreens
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import it.polito.did.gruppo8.util.myComposable.LoadingAnimation


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
                                .size(450.dp, 450.dp)
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
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth()

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

                                            Text(
                                                text = "+ 50",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )

                                            Spacer(modifier = Modifier.size(10.dp))
                                            Image(
                                                painter = painterResource(id = R.drawable.coin),
                                                contentDescription = "houseIcon" )

                                            Spacer(modifier = Modifier.size(10.dp))

                                            Text(
                                                text = "AND",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h6
                                            )

                                            Image(
                                                painter = painterResource(id = R.drawable.house),
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

                                            Text(
                                                text = "SKIP TURN",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h6
                                            )
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

                                            Text(
                                                text = "SKIP TURN",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h6
                                            )
                                            Image(

                                                painter = painterResource(id = R.drawable.coin),
                                                contentDescription = "coinIcon",
                                                modifier = Modifier.size(50.dp,50.dp),

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

                                            Text(
                                                text = "+ 50",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )

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

                                            Text(
                                                text = "- 25",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )

                                            Image(
                                                painter = painterResource(id = R.drawable.coin),
                                                contentDescription = "houseIcon" )

                                        }

                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceAround,
                                            verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "NO ANSWER",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h5
                                            )
                                            Spacer(modifier = Modifier.size(40.dp))

                                            // TODO:inserire valore delle monete

                                            Text(
                                                text = "NOTHING HAPPEN",
                                                fontFamily = caveatBold,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h6
                                            )

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


