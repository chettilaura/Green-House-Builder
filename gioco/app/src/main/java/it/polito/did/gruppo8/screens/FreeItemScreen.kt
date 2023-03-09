package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene quattro scelte nascoste casuali che vengono assegnate al giocatore a inizio partita
//4 card da scegliere che nascondono oggetti base casuali
//è presente anche didascalia che dice cosa fare al giocatore
//NOTA: lo sfondo è dello stesso colore del quartiere assegnato

@Composable
fun FreeItemScreen(modifier: Modifier = Modifier){
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}