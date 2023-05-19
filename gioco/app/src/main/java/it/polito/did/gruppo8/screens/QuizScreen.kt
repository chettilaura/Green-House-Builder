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
    val players by vm.players.observeAsState()
    val gameInfos by vm.gameInfos.observeAsState()

    val quiz = vm.currentQuiz
    var selectedAnswer by remember {
        mutableStateOf(-1)
    }
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
                RoundCard("${gameInfos!!.turnCounter}/${gameInfos!!.totalTurns}")
                Spacer(modifier = Modifier.weight(1f))
                MyTimerCard(gameInfos!!.quizTime) {
                    vm.onSubmitAnswerButtonPressed(
                        quiz,
                        selectedAnswer
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                MoneyCard(players!![vm.myPlayerId]!!.wallet.coins)
            }
            Spacer(modifier = Modifier.weight(1f))

            QuizCard(quiz) {
                for(answer in quiz.answers) {
                    val index = quiz.answers.indexOf(answer)
                    Button(onClick = {
                        selectedAnswer = if(selectedAnswer==index) -1 else index
                        Log.d("QuizScreen", "Selected answer: $selectedAnswer")
                    },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults
                            .buttonColors(backgroundColor =
                            colorResource(if(selectedAnswer==index) R.color.emerald else R.color.asparagus)),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Text(text = answer,
                            fontFamily = caveatBold,
                            color = Color.White,
                            style = MaterialTheme.typography.body2)
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            MyButton(
                title = "SUBMIT ANSWER",
                description = "SUBMIT ANSWER BUTTON") {
                vm.onSubmitAnswerButtonPressed(
                    quiz,
                    selectedAnswer
                )
            }
        }
    }
}

@Composable
//Bisogna passare alla funzione testo della domanda, testi delle risposte per popolare gli elementi
//Occorre anche passare risposta corretta al pulsante giusto
fun QuizCard(
    quiz: Quiz,
    answerButtons: @Composable() ()->Unit
) {
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
                Text(text = quiz.question,
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
                answerButtons.invoke()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen(GameViewModel())
}