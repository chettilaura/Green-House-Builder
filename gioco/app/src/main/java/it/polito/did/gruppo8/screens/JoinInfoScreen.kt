package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//Contiene info per entrare in una partita: box con nome della città, box con game ID, box con numero di case entrate in partita
//pulsante per impostazioni della partita

@Composable
fun JoinInfoScreen(modifier: Modifier = Modifier){
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}