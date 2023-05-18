package it.polito.did.gruppo8.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Quiz
import it.polito.did.gruppo8.util.myComposable.MoneyCard
import it.polito.did.gruppo8.util.myComposable.MyButton
import it.polito.did.gruppo8.util.myComposable.RoundCard
import it.polito.did.gruppo8.util.myComposable.MyTimerCard

//contiene quiz: un box contiene la domanda, 6 pulsanti con le risposte da scegliere, pulsante per saltare il quiz
//è presente anche indicazione del tempo rimanente per rispondere

@Composable
fun QuizScreen(vm: GameViewModel, modifier: Modifier = Modifier) {
    //variabili per modificare colore di sfondo dei button per le risposte quando ne viene selezionata una
    //sono passate al composable QuizCard, insieme alla funzione updateSelection che dovrebbe modificare
    //il flag di selezione
    var selected by remember { mutableStateOf(false) }
    var selectionColor = if(selected) R.color.emerald else R.color.asparagus

    val players by vm.players.observeAsState()
    val gameInfos by vm.gameInfos.observeAsState()

    val quiz = vm.currentQuiz
    Log.d("QuizScreen", "Question: ${quiz.question}")
    Log.d("QuizScreen", "Answers: ${quiz.answers}")


    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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

                RoundCard("${gameInfos!!.turnCounter}/${gameInfos!!.totalTurns}", "2")
                /*TurnCard(colorTurn = "RED", colorResId = R.color.old_rose
                    *//*TODO: passare parametro della squadra con turno attivo
                    *  il colore è in formato Int e il nome è contenuto nel file colors.xml dentro res,
                    *  il nome deve essere corrispondente al colore *//*)

                Spacer(modifier = Modifier.weight(1f))*/

                Spacer(modifier = Modifier.weight(1f))
                MyTimerCard(gameInfos!!.quizTime.toLong()*1000)
                Spacer(modifier = Modifier.weight(1f))
                MoneyCard(players!![vm.myPlayerId]!!.wallet.coins)
            }
            Spacer(modifier = Modifier.weight(1f))
            QuizCard(
                quiz.question,
                quiz.answers,
                selected,
                selectionColor
            ) {
                updateSelection -> selected = updateSelection
            }
            Spacer(modifier = Modifier.weight(1f))
            MyButton(
                title = "SUBMIT ANSWER",
                description = "SUBMIT ANSWER BUTTON",
                100,
                {})
            /*Button(onClick = { *//*TODO*//* },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.emerald)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)) {
                Text(text = "SUBMIT ANSWER", fontFamily = caveatBold, color = Color.White, style = MaterialTheme.typography.h6)
            }*/
        }
    }
}
/*}*/

@Composable
//Bisogna passare alla funzione testo della domanda, testi delle risposte per popolare gli elementi
//Occorre anche passare risposta corretta al pulsante giusto
fun QuizCard(
    question: String,
    answers: List<String>,
   /* colorResId: Int,*/
    selected: Boolean,
    selectionColor: Int,
    onUpdateMyVar: (Boolean) -> Unit
) {
    /*val color = colorResource(id = colorResId)*/
    /*val buttonColor = colorResource(id = selectionColor)*/

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.8f),
        backgroundColor = colorResource(id = R.color.emerald),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        border = BorderStroke(2.dp, Color.Black)
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
                        .wrapContentHeight()
                        .fillMaxSize(0.4f)
                        .padding(8.dp),
                    backgroundColor = colorResource(id = R.color.asparagus),
                    elevation = 5.dp,
                    border = BorderStroke(2.dp, Color.DarkGray),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = question,
                        Modifier.padding(8.dp),
                        fontFamily = caveatBold,
                        color = Color.White,
                        style = MaterialTheme.typography.body1)
                }
                /*Spacer(modifier = Modifier.size(8.dp))*/

                Column(     //BUTTON DELLE RISPOSTE
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                        //BUTTON 1
                        Button(onClick = {
                            onUpdateMyVar(!selected)
                                         /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                            colors = ButtonDefaults
                                .buttonColors(backgroundColor = colorResource(id = selectionColor)),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(2.dp, Color.Black)
                        ) {
                            Text(text = answers[0],
                                fontFamily = caveatBold,
                                color = Color.White,
                                style = MaterialTheme.typography.body2)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        //BUTTON 2
                        Button(onClick = {
                            onUpdateMyVar(!selected)
                            /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                                    colors = ButtonDefaults
                                    .buttonColors(backgroundColor = colorResource(id = selectionColor)),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(2.dp, Color.Black)
                        ) {
                            Text(text = answers[1],
                                fontFamily = caveatBold,
                                color = Color.White,
                                style = MaterialTheme.typography.body2)
                        }
                    //}
                    Spacer(modifier = Modifier.size(8.dp))
                        //BUTTON 3
                        Button(onClick = {
                            onUpdateMyVar(!selected)
                            /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            colors = ButtonDefaults
                                .buttonColors(backgroundColor = colorResource(id = selectionColor)),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(2.dp, Color.Black)
                        ) {
                            Text(text = answers[2],
                                fontFamily = caveatBold,
                                color = Color.White,
                                style = MaterialTheme.typography.body2)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        //BUTTON 4
                        Button(onClick = {
                            onUpdateMyVar(!selected)
                            /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            colors = ButtonDefaults
                                .buttonColors(backgroundColor = colorResource(id = selectionColor)),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(2.dp, Color.Black)
                        ) {
                            Text(text = answers[3],
                                fontFamily = caveatBold,
                                color = Color.White,
                                style = MaterialTheme.typography.body2)
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
    QuizScreen(GameViewModel())
}