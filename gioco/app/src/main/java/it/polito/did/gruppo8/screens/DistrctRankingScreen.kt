package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene la classifica dei quartieri. Contiene: grafica del podio con i quattro quartieri in ordine di arrivo,
//button per vedere la classifica e i punteggi dei singoli giocatori
//pulsante di quit per terminare definitivamente la partita e tornare al menu principale

@Composable
fun DistrictRankingScreen(modifier: Modifier = Modifier)
{
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}