package it.polito.did.gruppo8.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

//Schermata per le impostazioni iniziali dell'applicazione
//contiene button per tornare indietro, dropdown menu per la lingua, switch per attivare/disattivare sfx
//switch per vibrazione on/off
//button per credits

@Composable
fun SettingsScreen(modifier: Modifier = Modifier){
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //TODO
}

