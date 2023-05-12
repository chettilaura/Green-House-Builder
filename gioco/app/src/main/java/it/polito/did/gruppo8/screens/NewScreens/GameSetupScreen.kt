package it.polito.did.gruppo8.screens.NewScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Player
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.screens.caveatRegular
import it.polito.did.gruppo8.screens.caveatSemiBold
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import it.polito.did.gruppo8.util.myComposable.*


@Composable
fun GameSetupScreen(vm : GameViewModel, modifier: Modifier = Modifier)
{
    var nPlayers by remember { mutableStateOf(TextFieldValue("")) }

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

            Spacer(modifier = Modifier.size(30.dp))

            //TODO: anche questo box può essere generalizzato e spostato nel package util.myComposable
            CityNameField(vm.cityName.value!!, "Create house button")

            Spacer(modifier = Modifier.size(10.dp))

            /*
            TODO: I campi di testo Players e GameID non sono text field interagibili, servono solo a mostrare
             dei valori che vengono letti dal database, non decisi dall'utente.
             GameID viene generato come string randomica e deve essere mostrata a schermo nel campo di testo.
             I Players dovrebbero essere una lista di nickname che si va a popolare di volta in volta quando varia
             il database ogni volta che un player joina la partita.
             -Mattia
             */
            //MyFormLine("players", "players")

            /*
            TODO: playerNames deve essere una lista di tipo LiveData, che contiene le stringhe dei
             dei giocatori. Ancora da implementare l'aspetto grafico del composable che mostra
             a schermo la lista.
             Si potrebbe definire nel ViewModel nel modo seguente:
             val playerNames: LiveData <List<String>> = playerDataObserver.getPlayerNames()
             -Edo

             PlayerList(names = viewModel.players.value.map { it.name })
             */


            Spacer(modifier = Modifier.size(10.dp))
            /*TODO: passare parametro dentro info l'ID univoco della partita
            *  -Edo*/
            InformationCard(title = "GAME ID", info = "12345XYZ", 0.3f)

            //MyFormBox(title = "Game ID", label = "Id string (test ${gameId})", fieldValue = nPlayers,  fraction = 0.3f)
            Spacer(modifier = Modifier.size(30.dp))

//TUTORIAL POP UP
            var setGamePopUpControl by remember { mutableStateOf(false) }
            MyButton(title = "GAME SETUP", description = "Setup", {setGamePopUpControl = true})

            Spacer(modifier = Modifier.size(5.dp))

            if (setGamePopUpControl) {

                Popup(
                    alignment = Center,
                    onDismissRequest = { setGamePopUpControl = false }) {
                    SetUpPopUp()
                }
            }

            //StartGameButton(title = "START", description = "start game button", onStartButtonPressed)
            MyButton(title = "START", description = "start game button", {vm.onStartButtonPressed()})
        }

    }
}

//COMPOSABLES FUNCTIONS
@Composable
fun PlayersList(names: List<String>) {
    LazyColumn {
        items(names) { name ->
            Text(name)
        }
    }
}

@Composable
fun SetUpPopUp() {
    val shape = RoundedCornerShape(30.dp)
    //TODO: manca implementare la logica delle impostazioni della partita nel backend
    var tofill by remember { mutableStateOf("") }
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
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //TODO: sono campi fantasma per ora, perchè manca la logica in backend
                    MyFormLine(title ="Max Players" , label = "players", targetValue = tofill, {})
                    MyFormLine(title ="Turns" , label = "players", targetValue = tofill, {})
                    MyFormLine(title ="Time" , label = "players", targetValue = tofill, {})
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
                Text(title , //DA PASSARE DA SCHERMATA NEW GAME SCREEN
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
        GameSetupScreen(vm,modifier = Modifier)
    }
}

