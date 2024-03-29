package it.polito.did.gruppo8.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Item
import it.polito.did.gruppo8.model.baseClasses.Statistics
import it.polito.did.gruppo8.util.myComposable.MyButton
import it.polito.did.gruppo8.util.myComposable.MyItemListCard
import it.polito.did.gruppo8.util.myComposable.ParameterBars

//contiene quattro scelte nascoste casuali che vengono assegnate al giocatore a inizio partita
//4 card da scegliere che nascondono oggetti base casuali
//è presente anche didascalia che dice cosa fare al giocatore
//NOTA: lo sfondo è dello stesso colore del quartiere assegnato


/*TODO: da eliminare, non è implementata nel loop di gioco, */
@Composable
fun FreeItemScreen(modifier: Modifier = Modifier){

    //lista di 4 oggetti per test dell'interfaccia
    val itemList = listOf<Item>()

    //variabile per attivare la presenza delle barre dei parametri quando viene selezionato un oggetto
    //dalla lista
    var parameterBarVisibility by remember { mutableStateOf(true) }

    var openDialog by remember { mutableStateOf(false)  }

    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Inserire anche top bar?
            ParameterBars(Statistics(), 0.15f)
            Spacer(modifier = Modifier.weight(0.02f))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                backgroundColor = colorResource(id = R.color.cal_poly_green),
                shape = RoundedCornerShape(20.dp),
                elevation = 5.dp,
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "CHOOSE YOUR FREE ITEM",
                        color = Color.White,
                        fontFamily = caveatRegular,
                        style = MaterialTheme.typography.h4)
                    Spacer(modifier = Modifier.weight(0.1f))

                    Box(modifier = Modifier.fillMaxSize()
                    ){
                        MyItemListCard(itemList = itemList, true){openDialog = true}
                        if (openDialog){
                            AlertDialog(
                                onDismissRequest = {
                                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                                    // button. If you want to disable that functionality, simply use an empty
                                    // onCloseRequest.
                                    openDialog = false
                                },
                                title = {
                                    Text(text = "Dialog Title")
                                },
                                text = {
                                    Text("Here is a text ")
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            openDialog = false
                                        }) {
                                        Text("This is the Confirm Button")
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = {
                                            openDialog = false
                                        }) {
                                        Text("This is the dismiss Button")
                                    }
                                }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.02f))
            MyButton(title = "START", description = "confirm choice and start button", 100){}
        }
    }
}


@Preview (showBackground = true, showSystemUi = true)
@Composable
fun FreeItemScreenPreview() {
    FreeItemScreen()
}