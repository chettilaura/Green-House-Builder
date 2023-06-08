package it.polito.did.gruppo8.util.myComposable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.model.baseClasses.Player
import it.polito.did.gruppo8.model.baseClasses.Statistics
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.ui.theme.DarkGreen


//composable legata alla lista dei giocatori
//legata a dashboard dell'host e a game over

@Composable
fun MyPlayerData(players: List<Player>) {
    Column(
        modifier = Modifier
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            players.forEach { player ->
                Text(
                    text = player.nickname,
                    fontFamily = caveatBold,
                    color = DarkGreen,
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center
                )
                ParameterBars(player.house.stats, 0.1f)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewPlayerList(){
    MyPlayerData(listOf())
}