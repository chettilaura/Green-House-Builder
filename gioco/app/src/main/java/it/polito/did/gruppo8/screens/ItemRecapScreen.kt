package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene riassunto degli oggetti scelti: è di fatto schermata di caricamento non interagibile
//NOTA: questa pagina scompare dopo alcuni secondi
//NOTA2: lo sfondo è del colore del quartiere assegnato


@Composable
fun ItemRecapScreen(modifier: Modifier = Modifier) {
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

//TODO
}