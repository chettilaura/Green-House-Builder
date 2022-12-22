package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene il negozio: quattro possibili card da scegliere con oggetti casuali
//deve essere presente anche indicazione sul turno, sul tempo rimanente per scegliere cosa fare, soldi rimanenti


@Composable
fun ShopScreen(modifier: Modifier = Modifier) {
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

//TODO
}