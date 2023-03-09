package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//Contiene feedback sulla risposta data al quiz, diversa in base al turno attivo o meno del giocatore
//Turno attivo: Corretto -> contiene animazione e indicazione su cosa fare dopo (scegliere oggetto dal negozio)
//              Errore   -> contiene animazione e testo che dice che passi il turno
//Turno NON attivo: Corretto -> contiene animazione e testo con indicazione su monete quadagnate
//                  Errore   -> contiene animazione e testo con indicazione su monete perse


@Composable
fun QuizFeedbackScreen(modifier: Modifier = Modifier)
{
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}