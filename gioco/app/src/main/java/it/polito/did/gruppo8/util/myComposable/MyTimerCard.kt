package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatBold
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
//funzione per il timer
        /*TODO: impostare inizio del timer e cambio di schermata allo scadere del timer*/
fun rememberCountdownTimerState(
    initialMillis: Long,
    endCallback: () -> Unit,
    step: Long = 1000,
): MutableState<Long> {
    val timeLeft = remember { mutableStateOf(initialMillis) }
    LaunchedEffect(initialMillis, step) {
        while (isActive && timeLeft.value > 0) {
            timeLeft.value = (timeLeft.value - step).coerceAtLeast(0)
            delay(step)
        }
        endCallback.invoke()
    }
    return timeLeft
}

@Composable
fun MyTimerCard(seconds: Int, onFinishEvent: ()->Unit) {
    //valore del tempo rimanente
    val timeLeftMs by rememberCountdownTimerState ((seconds*1000).toLong(), onFinishEvent) //tempo in ms
    val showTime = timeLeftMs / 1000 //tempo in secondi

    Card(modifier = Modifier
        .width(100.dp)
        .height(80.dp)
        .padding(2.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.clock),
                contentDescription = "clock",
                modifier = Modifier.weight(0.8f),
                contentScale = ContentScale.FillHeight
            )
            /*Text(text = "TIMER", fontFamily = caveatRegular, style = MaterialTheme.typography.body1)*/
            Text(text = "$showTime" + " sec", fontFamily = caveatBold, style = MaterialTheme.typography.h6)
        }
    }
}