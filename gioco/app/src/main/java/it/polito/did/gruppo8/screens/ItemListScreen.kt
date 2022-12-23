package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene la lista degli oggetti posseduti dal giocatore. Contiene: box con barre di info sui parametri
//lista scrollabile di card che mostrano gli item, pulsante di back
//NOTA: lo sfondo è dello stesso colore del quartiere assegnato al giocatore

@Composable
fun ItemListScreen(modifier: Modifier = Modifier)
{
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}