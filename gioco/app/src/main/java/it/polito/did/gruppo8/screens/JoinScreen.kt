package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene form per inserire nome del giocatore, form per inserire ID della partita, pulsante per creare la casa
//eventualmente anche possibilità di leggere codice QR

@Composable
fun JoinScreen(modifier: Modifier = Modifier){
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}