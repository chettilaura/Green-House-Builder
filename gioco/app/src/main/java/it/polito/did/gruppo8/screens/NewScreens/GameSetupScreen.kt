package it.polito.did.gruppo8.screens.NewScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Player
import it.polito.did.gruppo8.screens.CreditsPopUp
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import it.polito.did.gruppo8.util.myComposable.InformationCard
import it.polito.did.gruppo8.util.myComposable.MyButton
import it.polito.did.gruppo8.util.myComposable.MyFormLine
import it.polito.did.gruppo8.util.myComposable.MyPlayerNameListCard
import it.polito.did.gruppo8.util.myComposable.MyTopBar


@Composable
fun GameSetupScreen(vm : GameViewModel, modifier: Modifier = Modifier) {
    val players by vm.players.observeAsState()

    var totalRounds by remember { mutableStateOf("") }
    var turnTime by remember { mutableStateOf("") }
    var quizTime by remember { mutableStateOf("") }
    var setGamePopUpControl by remember { mutableStateOf(false) }

    totalRounds = vm.gameInfos.value!!.totalRounds.toString()
    turnTime = vm.gameInfos.value!!.turnTime.toString()
    quizTime = vm.gameInfos.value!!.quizTime.toString()


    if (setGamePopUpControl) {
        Box(
            modifier = Modifier.fillMaxSize()
                .blur(4.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "Immagine di sfondo",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
            Popup(
                alignment = Center,
                onDismissRequest = { setGamePopUpControl = false },
                properties = PopupProperties(focusable = true)
            ) {

                Box(
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(width = 2.dp, color = Color.Black)
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.bg),
                        contentDescription = "Immagine di sfondo",
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.FillHeight
                    )
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(R.drawable.tut_background),
                            contentDescription = "tutorial background",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            MyFormLine(
                                title = "Total rounds",
                                label = "rounds",
                                targetValue = totalRounds
                            ) { value -> totalRounds = value }
                            MyFormLine(
                                title = "Quiz time",
                                label = "seconds",
                                targetValue = quizTime
                            ) { value -> quizTime = value }
                            MyFormLine(
                                title = "Turn time",
                                label = "seconds",
                                targetValue = turnTime
                            ) { value -> turnTime = value }
                        }
                    }


                    /*
            SetUpPopUp(totalRounds, turnTime, quizTime){ newTotalRounds, newTurnTime, newQuizTime ->
                totalRounds = newTotalRounds
                turnTime = newTurnTime
                quizTime = newQuizTime
            }
             */
                }
            }
        }
    } else {


        Box(modifier = Modifier.fillMaxSize(1f)) {
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "Immagine di sfondo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )

            MyTopBar(title = "NEW GAME", colorId = colorResource(id = R.color.cal_poly_green))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.size(60.dp))

                //TODO: anche questo box può essere generalizzato e spostato nel package util.myComposable
                CityNameField(vm.gameInfos.value!!.cityName!!, "Create house button")
                Spacer(modifier = Modifier.size(10.dp))

                MyPlayerNameListCard(
                    header = "PLAYERS\n${players?.values!!.size}",
                    playersList = players?.values!!.toList()
                )
                Spacer(modifier = Modifier.size(10.dp))

                InformationCard(title = "GAME ID", info = vm.gameInfos.value!!.lobbyId!!, 0.3f)
                Spacer(modifier = Modifier.size(30.dp))


                MyButton(title = "GAME SETUP", description = "Setup", 100) {
                    setGamePopUpControl = true
                }

                Spacer(modifier = Modifier.size(5.dp))

                //Game Setup Pop Up
                MyButton(
                    title = "START",
                    description = "start game button",
                    100,
                    enabled = players!!.values.isNotEmpty()
                )
                {
                    vm.onStartButtonPressed(totalRounds.toInt(), turnTime.toInt(), quizTime.toInt())
                }
            }
        }
    }
}

//COMPOSABLES FUNCTIONS
@Composable
fun PlayersList(players: List<Player>) {
    LazyColumn {
        items(players) { player ->
            Text(player.nickname)
        }
    }
}

@Composable
fun SetUpPopUp(totalRounds: Int,
               turnTime: Int,
               quizTime: Int,
               updateGameInfos: (newTotalRounds:Int, newTurnTime:Int, newQuizTime:Int)->Unit
) {
    val shape = RoundedCornerShape(25.dp)

    var totalRounds_temp by remember { mutableStateOf(totalRounds.toString()) }
    var turnTime_temp by remember { mutableStateOf(turnTime.toString()) }
    var quizTime_temp by remember { mutableStateOf(quizTime.toString()) }

    Box(
        modifier = Modifier
            .size(400.dp, 400.dp)
            .clip(shape)
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.tut_background),
                contentDescription = "tutorial background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //TODO: sono campi fantasma per ora, perchè manca la logica in backend
                MyFormLine(title ="Total rounds" , label = "rounds", targetValue = totalRounds_temp){value -> totalRounds_temp = value}
                MyFormLine(title ="Quiz time" , label = "seconds", targetValue = quizTime_temp){value -> quizTime_temp = value}
                MyFormLine(title ="Turn time" , label = "seconds", targetValue = turnTime_temp){value -> turnTime_temp = value}
                updateGameInfos.invoke(totalRounds_temp.toInt(), turnTime_temp.toInt(), quizTime_temp.toInt())
            }
        }
    }
}

@Composable
fun CityNameField(title: String, description: String) {
    Box(modifier = Modifier
        .height(100.dp)
    ){
        Image(
            painter = painterResource(R.drawable.name_of_the_city_tabs),
            contentDescription = description,
            Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(title , //DA PASSARE DA SCHERMATA NEW GAME SCREEN
                    fontFamily = caveatBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h4)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun StartGameButton(title: String, description: String, onButtonPressed: () -> Unit) {
    Box(modifier = Modifier
        .height(100.dp)
    ){
        Image(
            painter = painterResource(R.drawable.empty_button),
            contentDescription = description,
            Modifier
                .fillMaxSize()
                .clickable { onButtonPressed() }
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(title, //DA PASSARE DA SCHERMATA NEW GAME SCREEN
                    fontFamily = caveatBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h4)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}


/*--------------------------------------------------------------------*/
//PREVIEW

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGameSetupScreen() {
    GameSkeletonTheme {
        val vm = GameViewModel()
        GameSetupScreen(vm, modifier = Modifier)
    }
}

