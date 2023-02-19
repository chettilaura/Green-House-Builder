package it.polito.did.gruppo8.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene quiz: un box contiene la domanda, 6 pulsanti con le risposte da scegliere, pulsante per saltare il quiz
//è presente anche indicazione del tempo rimanente per rispondere

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {
    GenericScreen(title = "Rispondi alla domanda"){
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ){
            Column(modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                QuizCard()
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Salta domanda")
                }
            }
        }
    }
}

@Composable
//Bisogna passare alla funzione testo della domanda, testi delle risposte per popolare gli elementi
//Occorre anche passare risposta corretta al pulsante giusto
fun QuizCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.DarkGray,
        elevation = 5.dp,
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp),
                    backgroundColor = Color.Yellow,
                    elevation = 5.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Questa è la domanda del quiz che deve essere pescata dal database",
                        Modifier.padding(16.dp))
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Risposta 1")
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Risposta 2")
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Risposta 3")
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Risposta 4")
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Risposta 5")
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Risposta 6")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen()
}