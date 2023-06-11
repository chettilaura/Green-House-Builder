package it.polito.did.gruppo8.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Quiz
import it.polito.did.gruppo8.model.baseClasses.Statistics
import it.polito.did.gruppo8.screens.NewScreens.DashBoardTopBar
import it.polito.did.gruppo8.util.myComposable.ParameterBars

/*TODO: Schermata di test per provare elementi grafici*/
/*TODO: serve per testare composable, si può eliminare se funziona tutto*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestScreen() {
    /*val houses = remember { mutableStateListOf<String>("Alice", "Bob", "Charlie", "David", "Emma",
            "Frank", "Grace", "Henry", "Ivy", "Jack",
            "Kate", "Liam", "Mia", "Noah", "Olivia")
    }*/

    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        DashBoardTopBar(title = "city of testlandiaa", colorId = colorResource(id = R.color.cal_poly_green))
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                            .fillMaxSize(0.5f)
                            .padding(8.dp),
                        backgroundColor = colorResource(id = R.color.asparagus),
                        elevation = 5.dp,
                        border = BorderStroke(2.dp, Color.DarkGray),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "quiz.questionaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                                Modifier.padding(8.dp),
                                fontFamily = caveatRegular,
                                color = Color.White,
                                style = MaterialTheme.typography.h4
                            )
                        }
                    }

                    /*var index = 6
            Card(modifier = Modifier
                .padding(4.dp)
                .wrapContentHeight(),
                elevation = 4.dp,
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(2.dp,
                    when(index){
                        0 -> Color(0xFFDAA520)
                        1 -> Color(0xFFC0C0C0)
                        2 -> Color(0xFFCD7F32)
                        else -> {
                            Color.Black
                        }
                    }
                )
            ){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if (index <= 2) {
                                Image(
                                    painter = painterResource(
                                        when (index) {
                                            0 -> R.drawable.coccarda1
                                            1 -> R.drawable.coccarda2
                                            2 -> R.drawable.coccarda3
                                            else -> {
                                                0
                                            }
                                        }
                                    ),
                                    contentDescription = "Award",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Text(
                                text = "NOME GIOCATORE ",
                                fontFamily = caveatBold,
                                color = Color.Black,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Start
                            )

                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "SCORE: ",
                                fontFamily = caveatSemiBold,
                                color = Color.Black,
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = "PUNTEGGIO",
                                fontFamily = caveatBold,
                                color = Color.Black,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Start
                            )
                        }
                        ParameterBars(*//*TODO: passare statistiche relative a player*//*Statistics(),
                            0.2f
                        )
                    }
                }
            }*/
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestScreenPreview() {
    TestScreen()
}