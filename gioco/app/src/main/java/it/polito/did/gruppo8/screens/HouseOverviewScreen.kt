package it.polito.did.gruppo8.screens

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.*

//Contiene vista generale della propria casa. Contiene: box con info sui parametri (barre),
//box con casetta, timer, soldi e info sul round di gioco
//pulsante che porta alla lista degli oggetti
//box con info sulla stagione nel gioco e fun fact (da confermare)
//possibilità di swipe a sinistra sulla pagina per osservare lista delle case della città:
//      contiene box con info sui parametri del giocatore, timer per il tempo del turno, lista degli altri giocatori, con nomi e barre per parametri
//      pulsanti per mostrare solo i giocatori di un determinato quartiere
//      pulsante di back/possibilità di swipare a destra per tornare alla vista precedente
//NOTA: lo sfondo è dello stesso colore del quartiere assegnato al giocatore

@Composable
fun HouseOverviewScreen(modifier: Modifier = Modifier)
{
    GenericScreen(title = "Overview"){
        ParameterBars(param = listOf("Costo", "Emissioni", "Comfort"))
    }
}


@Composable
fun ParameterBars(param: List<String>) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(  //Parametro 1
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = 0.5f,  //TODO: passare qui valore della lunghezza della barra
                    color = Color.Yellow,
                    modifier = Modifier.fillMaxWidth(0.5f)
                        .size(10.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = param[0],
                    style = MaterialTheme.typography.h5)
            }
            Row(  //Parametro 2
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = 0.7f, //TODO: passare qui valore della lunghezza della barra
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth(0.5f)
                        .size(10.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = param[1],
                    style = MaterialTheme.typography.h5)
            }
            Row( //Parametro 3
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = 0.4f, //TODO: passare qui valore della lunghezza della barra
                    color = Color.Blue,
                    modifier = Modifier.fillMaxWidth(0.5f)
                        .size(10.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = param[2],
                    style = MaterialTheme.typography.h5)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HouseOverviewScreenPreview() {
    HouseOverviewScreen()
}