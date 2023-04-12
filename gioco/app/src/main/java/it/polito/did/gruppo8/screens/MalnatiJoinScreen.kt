package it.polito.did.gruppo8.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

//contiene form per inserire nome del giocatore, form per inserire ID della partita, pulsante per creare la casa
//eventualmente anche possibilità di leggere codice QR

@Composable
fun MalnatiJoinScreen(
    onJoinGame: (String, String) -> Unit,
    modifier: Modifier = Modifier
){
    var gameID by remember { mutableStateOf(TextFieldValue("")) }
    var playerName by remember { mutableStateOf(TextFieldValue("")) }

    GenericScreen(title = "Partecipa alla partita"){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                    Text(text = "Come ti vuoi chiamare?")
                }
                TextField(
                    value = playerName,
                    onValueChange = { playerName = it},
                    label = { Text(text = "Nome del giocatore")},
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Row {
                    Divider(modifier =
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 32.dp)
                        .align(Alignment.CenterVertically)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "Inserisci ID della partita")
                }
                TextField(
                    value = gameID,
                    onValueChange = { gameID = it},
                    label = { Text(text = "ID della partita")},
                    placeholder = { Text(text = "000 000")}
                    )
                Spacer(modifier = Modifier.padding(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = { onJoinGame(gameID.text, playerName.text) }
                    ) {
                        Text(text = "Partecipa!")
                        //NOTA: deve funzionare solo se i campi sono compilati correttamente
                    }
                }

                Row {
                    Divider(modifier =
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterVertically)
                    )
                    Text("oppure")
                    Divider(modifier =
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterVertically)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Scansiona il codice QR")
                    }
                }


            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun JoinScreenPreview(modifier: Modifier = Modifier) {
    GameSkeletonTheme() {
        MalnatiJoinScreen(fun (_:String, _:String){})
    }
}