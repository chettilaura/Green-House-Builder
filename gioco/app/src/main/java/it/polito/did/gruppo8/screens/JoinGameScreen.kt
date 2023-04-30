package it.polito.did.gruppo8.screens

import android.accounts.AuthenticatorDescription
import androidx.compose.foundation.*
/*import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value*/
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import it.polito.did.gruppo8.R.color.*


//contiene form per inserire nome del giocatore, form per inserire ID della partita, pulsante per creare la casa
//eventualmente anche possibilità di leggere codice QR

//fai riferimento a MalnatiJoinScreen ->
@Composable
fun JoinGameScreen(
    /*TODO: controllare se il passaggio di onJoinGame al button è corretta - Edo*/
    onJoinGame: (String, String) -> Unit,
    modifier: Modifier = Modifier
){
    var gameID by remember { mutableStateOf(TextFieldValue("")) }
    var playerName by remember { mutableStateOf(TextFieldValue("")) }

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
            FormCard ("PLAYER NAME", "NAME", playerName, 0.3f)
            Spacer(modifier = Modifier.size(32.dp))

            FormCard ("GAME ID", "ID NUMBER", gameID, 0.4f)

        }
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom) {
        CustomButton ("CREATE HOUSE", "Create house button", gameID, playerName, onJoinGame)
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JoinGameScreenPreview(modifier: Modifier = Modifier) {
    GameSkeletonTheme() {
        JoinGameScreen( fun(_:String, _:String){} ) //se metti dei parametri qui vanno le graffe ({},{})
    }
}

@Composable
fun MyTopBar(title: String, colorId: Color) {
    TopAppBar (
        modifier = Modifier.height(100.dp),
        title = {
            Text(title,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h2)
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back button",
                modifier = Modifier
                    .padding(3.dp)
                    .clickable { /*TODO: tornare a schermata precedente*/ }
            )
        },
        backgroundColor = colorId,
        elevation = 40.dp
    )
}

/*
TODO: Il component FormCard (che è un bene sia generalizzato) ha un problema intrinseco nel parametro
 fieldValue. Testandolo, si prova che in realtà il parametro fieldValue non registra il contenuto del
 campo di testo. Questo perchè all'interno del codice del component viene ridichiarata un'altra variabile
 mutableStateOf con lo stesso nome. Cancellare la dichiarazione della variabile tuttavia non basta,
 poichè nella onValueChange non è possibile riassegnare il parametro della funzione fieldValue in quanto
 i parametri sono sempre dei val, non possono mutare come i var.
 -Mattia
 */
@Composable
public fun FormCard (title: String, label: String, fieldValue: TextFieldValue, fraction:Float) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(fraction)
        .padding(2.dp),
        backgroundColor = colorResource(id = emerald),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Black)) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontFamily = caveatSemiBold,
                color = Color.White,
                style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.size(4.dp))

            var fieldValue by remember { mutableStateOf(TextFieldValue(""))}
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    /*.background(colorResource(id = emerald))*/
                    .border(
                        width = 2.dp,
                        color = colorResource(id = cal_poly_green),
                        shape = RoundedCornerShape(10.dp)
                    ),
                value = fieldValue,
                onValueChange = { fieldValue = it },
                label = {
                    Text(text = label,
                    color = Color.White,
                    fontFamily = caveatRegular,
                    style = MaterialTheme.typography.body1
                )},
                /*colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = asparagus)
                )*/
            )
        }
    }
}

@Composable
fun CustomButton(title: String,
                 description: String,
                 gameID: TextFieldValue,
                 playerName: TextFieldValue,
                 onJoinGame: (String, String) -> Unit) {
    Box(modifier = Modifier
        .height(100.dp)
    ){
        Image(
            painter = painterResource(R.drawable.empty_button),
            contentDescription = description,
            modifier = Modifier
                .clickable { onJoinGame(gameID.text, playerName.text) }
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
                Text(title,
                    fontFamily = caveatBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h4)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}