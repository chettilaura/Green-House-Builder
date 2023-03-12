package it.polito.did.gruppo8.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

//onStartNewGame è un nome locale per indicare che riceve da mainscreen "onCreateNewGame" che è def in view model che corrisponde a "createNewGame" in game manager
//funzioni composable accettano un Modifier

@Composable
fun InitialScreen(onStartNewGame: () -> Unit,  onJoinGame: (String) -> Unit,  modifier: Modifier = Modifier)
{
    //"matchId" è un LiveData definito in gameManager
    // LiveData + "remember" -> indico ricomposizione quando avviene modifica matchId (modifica id partita)
    val matchId = remember {
        mutableStateOf("")
    }

    //vedi "GenericScreen.kt" per vedere struttura (Scaffold etc.)
    GenericScreen(title = "Initial Screen") {
        Column(modifier.fillMaxWidth()) {
            Spacer(Modifier.weight(1f))
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onStartNewGame ) {
                Text("Start new match")
            }
            Spacer(Modifier.height(32.dp))
            Row {
                Divider(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterVertically)
                )
                Text("or")
                Divider(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            Spacer(
                Modifier.height(32.dp)
                //.background(color = Color.Yellow)
            )
            TextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                singleLine = true,
                value = matchId.value,
                onValueChange = {matchId.value = it})
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {onJoinGame(matchId.value)}) {
                Text("Join match")
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

//"fun GameSkeletonTheme" definita in "Theme.kt"
@Preview(showBackground = true)
@Composable
fun PreviewInitialScreen() {
    GameSkeletonTheme {
        InitialScreen({},{})
    }

}