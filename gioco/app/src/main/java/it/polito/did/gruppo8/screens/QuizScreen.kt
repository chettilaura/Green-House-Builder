package it.polito.did.gruppo8.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R

//contiene quiz: un box contiene la domanda, 6 pulsanti con le risposte da scegliere, pulsante per saltare il quiz
//è presente anche indicazione del tempo rimanente per rispondere

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {
    /*GenericScreen(title = "Quiz Time!"){*/
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "Immagine di sfondo",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillBounds
            )

            Column(modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .padding(1.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    TurnCard(colorTurn = "RED", colorResId = R.color.old_rose
                        /*TODO: passare parametro della squadra con turno attivo
                        *  il colore è in formato Int e il nome è contenuto nel file colors.xml dentro res,
                        *  il nome deve essere corrispondente al colore */)

                    Spacer(modifier = Modifier.weight(1f))
                    RoundCard("1/8"/*TODO: passare parametro del numero di turno*/)
                    Spacer(modifier = Modifier.weight(1f))
                    TimerCard(/*TODO: passare parametro del tempo rimanente per la fine del turno*/)
                    Spacer(modifier = Modifier.weight(1f))
                    MoneyCard("534"/*TODO: passare valore dei soldi*/)
                }
                QuizCard(
                    "Questa è la domanda del quiz che deve essere pescata dal database",
                    listOf(
                        "This is Answer number 1",
                        "This is Answer number 2",
                        "This is Answer number 3",
                        "This is Answer number 4"),
                    R.color.old_rose
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Salta domanda")
                }
            }
        }
    }
/*}*/

@Composable
//Bisogna passare alla funzione testo della domanda, testi delle risposte per popolare gli elementi
//Occorre anche passare risposta corretta al pulsante giusto
fun QuizCard(question : String, answers : List<String>, colorResId : Int ) {
    val color = colorResource(id = colorResId)
    Card(
        modifier = Modifier.fillMaxSize(0.9f),
        backgroundColor = color,
        elevation = 5.dp,
        border = BorderStroke(2.dp, Color.DarkGray),
        shape = RoundedCornerShape(15.dp)
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(   //TESTO DELLA DOMANDA
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(0.6f)
                        .padding(16.dp),
                    backgroundColor = Color.White,
                    elevation = 5.dp,
                    border = BorderStroke(2.dp, Color.DarkGray),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = question,
                        Modifier.padding(16.dp))
                }
                /*Spacer(modifier = Modifier.size(8.dp))*/

                Column(     //BUTTON DELLE RISPOSTE
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Button(onClick = { /*TODO*/ },
                        modifier = Modifier.weight(0.5f)) {
                            Text(text = answers[0])
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = { /*TODO*/ },
                            modifier = Modifier.weight(0.5f)) {
                            Text(text = answers[1])
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Button(onClick = { /*TODO*/ },
                            modifier = Modifier.weight(0.5f)) {
                            Text(text = answers[2])
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = { /*TODO*/ },
                            modifier = Modifier.weight(0.5f)) {
                            Text(text = answers[3])
                        }
                    }
                }
                /*Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { *//*TODO*//* }) {
                        Text(text = "Risposta 5")
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(onClick = { *//*TODO*//* }) {
                        Text(text = "Risposta 6")
                    }
                }*/
            }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen()
}