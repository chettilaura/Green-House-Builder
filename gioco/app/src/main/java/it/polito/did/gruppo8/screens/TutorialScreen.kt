package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//Contiene due button per selezionare quale tutorial mostrare, un pulsante di back e un box che contiene il testo del tutorial
//eventualmente si può aggiungere un pulsante per creare una partita o fare join

@Composable
fun TutorialScreen(modifier: Modifier = Modifier){
    val vm: GameViewModel = viewModel()

    //TODO
}