package it.polito.did.gruppo8.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import it.polito.did.gruppo8.util.myComposable.MyButton


@Composable
fun MainMenuScreen(vm:GameViewModel, modifier: Modifier = Modifier) {

    var settingsPopUpControl by remember { mutableStateOf(false) }
    var tutorialPopUpControl by remember { mutableStateOf(false) }

    if (settingsPopUpControl) {
        Box(modifier = Modifier.fillMaxSize()
            .blur(4.dp)) {
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "Immagine di sfondo",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
            Popup(
                alignment = Center,
                onDismissRequest = { settingsPopUpControl = false }) {

                CreditsPopUp()

            }
        }
    }

    else if (tutorialPopUpControl) {

        Box(modifier = Modifier.fillMaxSize()
            .blur(4.dp)) {
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "Immagine di sfondo",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
            Popup(
                alignment = Center,
                onDismissRequest = { tutorialPopUpControl = false }) {
                popUpTutorial()
            }
        }
    } else {

        Box(modifier = Modifier.fillMaxSize(1f)) {
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "Immagine di sfondo",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(30.dp))

                AppName()

                MyButton("NEW GAME", "Create house button", 100) { vm.onNewGameButtonPressed() }
                MyButton("JOIN GAME", "Create house button", 100) { vm.onJoinGameButtonPressed() }
                Spacer(modifier = Modifier.size(30.dp))

//TUTORIAL POP UP
                MyButton(title = "GUIDE", description = "Tutorial", 100) {
                    tutorialPopUpControl = true
                }
                Spacer(modifier = Modifier.size(5.dp))

//SETTINGS POP UP

                MyButton(title = "CREDITS", description = "Credits", 100) {
                    settingsPopUpControl = true
                }
                Spacer(modifier = Modifier.size(5.dp))
            }
        }
    }
}

//COMPOSABLES FUNCTIONS

/*
    TODO: Valutare un metodo per generalizzare anche il PopUp, se fattibile.
     -Mattia
 */
@Composable
fun popUpTutorial() {
    val shape = RoundedCornerShape(20.dp)
    Card(
        modifier = Modifier
            .size(400.dp, 400.dp).padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Black),
        backgroundColor = colorResource(id = R.color.cal_poly_green)
            /*.clip(shape).border(
                shape = shape,
                border = BorderStroke(width = 2.dp, color = Color.Black)
            )
            .background(Color(R.color.cal_poly_green))*/
    ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "GUIDE",
                            fontFamily = caveatBold,
                            color = Color.White,
                            style = MaterialTheme.typography.h3
                        )
                    }
                    Instructions()
                }
            }
    }
}

@Composable
fun CreditsPopUp() {
    val shape = RoundedCornerShape(20.dp)
    Card(
        modifier = Modifier
            .size(400.dp, 400.dp).padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Black),
        backgroundColor = colorResource(id = R.color.cal_poly_green)
            /*.clip(shape)
            .border(
                shape = shape,
                border = BorderStroke(width = 2.dp, color = Color.Black)
            )
            .background(Color(R.color.cal_poly_green))*/
    ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "CREDITS",
                            fontFamily = caveatBold,
                            color = Color.White,
                            style = MaterialTheme.typography.h3
                        )
                    }
                    Column(modifier = Modifier
                        .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally) {


                        Text("EDOARDO ARALLA",
                            fontFamily = caveatRegular,
                            color = Color.White,
                            style = MaterialTheme.typography.h5)

                        Text("SACHA BARIL",
                            fontFamily = caveatRegular,
                            color = Color.White,
                            style = MaterialTheme.typography.h5)

                        Text("MATTIA CACCIATORE",
                            fontFamily = caveatRegular,
                            color = Color.White,
                            style = MaterialTheme.typography.h5)


                        Text("LAURA MARCHETTI",
                            fontFamily = caveatRegular,
                            color = Color.White,
                            style = MaterialTheme.typography.h5)

                        Text("GIOVANNI MERCORILLO",
                            fontFamily = caveatRegular,
                            color = Color.White,
                            style = MaterialTheme.typography.h5)

                    }
                }
            }
    }
}

@Composable
fun AppName(){
    Row(horizontalArrangement = Arrangement.Center) {

        Image(
            painter = painterResource(id = R.drawable.app_name),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )

    }
}

@Composable
fun Instructions(){
    Column(modifier = Modifier
        .padding(8.dp)
        .verticalScroll(rememberScrollState())) {

        Text(text = "HOST",
            fontFamily = caveatBold,
            color = Color(0xFF43C366),
            style = MaterialTheme.typography.h5)

        Text(text = "Create new city clicking on the new game button, set up your match with your customs total rounds, quiz time and turn time clicking on Game setup button and start the game. You’ll see a dashboard with all players statistics during the game. \n",
            fontFamily = caveatRegular,
            color = Color.White,
            style = MaterialTheme.typography.h5)

        Text(text = "PLAYER",
            fontFamily = caveatBold,
            color = Color(0xFF43C366),
            style = MaterialTheme.typography.h5)

        Text(text = "To start playing, click on “Join Game” button, write your player name and the Game ID of the reference match and create house. \n" +
        "Once your player is settled, you can answer the quizzes all the time. \n" +
                "When is your turn you can earn money if the answer is right or skip the turn when is wrong or don't submit answer.\n For the first case you can see your house stats and upgrade it by buying items from the shop trying to make it as green as possible. \n" +
                "When it is not your turn, you have the same possibilities of answering but you can’t go to the shop in case of right answer and you lose money if the answer is wrong, so just wait for you’re turn and don't submit answer if you're not sure. \n" +
                "At the game ending all players standings will be visible and you’ll see your position among others. \n" +
                "So, enjoy the match and MAKE IT GREEN! \n",
        fontFamily = caveatRegular,
        color = Color.White,
        style = MaterialTheme.typography.h5)
            }
}

/*--------------------------------------------------------------------*/
//PREVIEW

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOpenScreen() {
    //val navigator = Navigator()
    val vm = GameViewModel()
    GameSkeletonTheme {
        //MainMenuScreen(navController = rememberNavController())
        MainMenuScreen(vm)
    }
}

