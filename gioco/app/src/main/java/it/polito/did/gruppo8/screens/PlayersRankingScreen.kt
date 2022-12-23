package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//Classifica dei giocatori singoli. Contiene: pulsante back per tornare alla vista della classifica dei quartieri
//due button per filtrare la classifica che viene mostrata nel box sotto:
//      - Quartiere: mostra lista dei giocatori del quartiere selezionato (tramite checkbox?), in ordine dal migliore al peggiore
//      - Generale: si resettano i filtri
//DOMANDA: Premendo su un giocatore è possibile osservare la lista degli oggetti acquistati e info approfondite sui punteggi ottenuti?
//pulsante di quit per terminare definitivamente la partita e tornare al menu principale

@Composable
fun PlayersRankingScreen(modifier: Modifier = Modifier)
{
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}