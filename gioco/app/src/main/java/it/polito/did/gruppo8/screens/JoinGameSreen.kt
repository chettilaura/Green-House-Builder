package it.polito.did.gruppo8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
//fai riferimento a MalnatiJoinScreen ->
@Composable
fun JoinGameScreen(modifier: Modifier = Modifier){
    var gameID by remember { mutableStateOf(TextFieldValue("")) }
    var playerName by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JoinGameScreenPreview(modifier: Modifier = Modifier) {
    GameSkeletonTheme() {
        JoinGameScreen() //se metti dei parametri qui vanno le graffe ({},{})
    }
}