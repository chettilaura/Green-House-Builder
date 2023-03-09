package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene impostazioni per la partita: dropdown per impostare il tempo di azione per ogni turno, impostazione per numero di turni della partita
//pulsante per iniziare la partita
//4 box che contengono il numero di giocatori in partita per ogni quartiere

@Composable
fun GameSetupScreen(modifier: Modifier = Modifier){
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}