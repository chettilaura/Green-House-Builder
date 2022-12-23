package it.polito.did.gruppo8.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//Pagina di caricamento generica, utilizzabile per diversi contesti:
//Caricamento in attesa di inizio partita, dopo aver fatto join: contiene info sul quartiere assegnato e animazione di caricamento
//Caricamento nuova partita: contiene animazione di caricamento
//Caricamento del quiz: contiene animazione di caricamento e indicazione sul quartiere di cui è il turno attivo
    //Se non è turno del giocatore, contiene pulsante per skip del quiz e descrizione su cosa fare

@Composable
fun GenericLoadingScreen(modifier: Modifier = Modifier)
{
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}