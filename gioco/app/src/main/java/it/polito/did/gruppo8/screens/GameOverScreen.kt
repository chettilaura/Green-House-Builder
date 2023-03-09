package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//pagina non interagibile che dice che la partita è finita. Contiene solo testo e animazione per il caricamento della classifica finale

@Composable
fun GameOverScreen(modifier: Modifier = Modifier)
{
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}