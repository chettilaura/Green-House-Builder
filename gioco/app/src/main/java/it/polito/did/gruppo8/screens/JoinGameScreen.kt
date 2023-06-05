package it.polito.did.gruppo8.screens

import androidx.compose.foundation.*
/*import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value*/
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import it.polito.did.gruppo8.R.color.*
import it.polito.did.gruppo8.util.myComposable.*
import it.polito.did.gruppo8.GameViewModel


//contiene form per inserire nome del giocatore, form per inserire ID della partita, pulsante per creare la casa

@Composable
fun JoinGameScreen(vm : GameViewModel, modifier: Modifier = Modifier) {

    var gameID by remember { mutableStateOf("") }
    var playerName by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        MyTopBar(title = "JOIN GAME", colorId = colorResource(id = cal_poly_green))
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyFormBox("PLAYER NAME", "NAME", playerName, {playerName = it}, 0.3f)
            Spacer(modifier = Modifier.size(32.dp))
            MyFormBox("GAME ID", "ID NUMBER", gameID, {gameID = it}, 0.4f)

        }
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom) {
        MyButton("CREATE HOUSE", "Create house button",100) {vm.onCreateHouseButtonPressed(gameID,playerName)}
    }

        Spacer(modifier = Modifier.size(8.dp))
    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JoinGameScreenPreview(modifier: Modifier = Modifier) {
    val vm = GameViewModel()
    GameSkeletonTheme() {
        JoinGameScreen(vm)
    }
}