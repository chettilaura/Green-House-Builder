package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene quiz: un box contiene la domanda, 6 pulsanti con le risposte da scegliere, pulsante per saltare il quiz
//è presente anche indicazione del tempo rimanente per rispondere

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

//TODO
}