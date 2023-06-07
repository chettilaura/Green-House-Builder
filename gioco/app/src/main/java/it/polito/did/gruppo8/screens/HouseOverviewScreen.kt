package it.polito.did.gruppo8.screens

import MyAlertDialog
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Item
import it.polito.did.gruppo8.util.myComposable.MoneyCard
import it.polito.did.gruppo8.util.myComposable.MyButton
import it.polito.did.gruppo8.util.myComposable.MyItemListCard
import it.polito.did.gruppo8.util.myComposable.MyTimerCard
import it.polito.did.gruppo8.util.myComposable.ParameterBars
import it.polito.did.gruppo8.util.myComposable.RoundCard

//Contiene vista generale della propria casa. Contiene: box con info sui parametri (barre),
//box con casetta, timer, soldi e info sul round di gioco
//pulsante che porta alla lista degli oggetti
//box con info sulla stagione nel gioco e fun fact (da confermare)
//possibilità di swipe a sinistra sulla pagina per osservare lista delle case della città:
//      contiene box con info sui parametri del giocatore, timer per il tempo del turno, lista degli altri giocatori, con nomi e barre per parametri
//      pulsanti per mostrare solo i giocatori di un determinato quartiere
//      pulsante di back/possibilità di swipare a destra per tornare alla vista precedente
//NOTA: lo sfondo è dello stesso colore del quartiere assegnato al giocatore

/*TODO: da ripulire e spostare in NewScreen*/

val caveatRegular = FontFamily(
    Font(R.font.caveat_regular, FontWeight.Normal, FontStyle.Normal)
)
val caveatMedium = FontFamily(
    Font(R.font.caveat_medium, FontWeight.Medium, FontStyle.Normal)
)
val caveatSemiBold = FontFamily(
    Font(R.font.caveat_semibold, FontWeight.SemiBold, FontStyle.Normal)
)
val caveatBold = FontFamily(
    Font(R.font.caveat_bold, FontWeight.Bold, FontStyle.Normal)
)

@Composable
fun HouseOverviewScreen(vm: GameViewModel, modifier: Modifier = Modifier)
{
    /*
    val colorTurn = rememberSaveable {
        mutableStateOf ("RED")
    }
    val numeroTurno = rememberSaveable {
        mutableStateOf("1/8")
    }
    val money = rememberSaveable{
        mutableStateOf (534)
    }
     */
    val gameInfos by vm.gameInfos.observeAsState()
    val players by vm.players.observeAsState()

    //lista di 20 oggetti per test dell'interfaccia
    //val itemList = generateItemList(20)
    val shop by vm.shop.observeAsState()

    //controllo per visualizzare casa o lista di oggetti acquistati
    var houseVisibility by remember { mutableStateOf(true) }

    //controllo per visualizzare shop
    var shopVisibility by remember { mutableStateOf(false) }
    //controllo abilitazione button dello shop
    var disableButton by remember { mutableStateOf(true) }

    var selectedItem by remember { mutableStateOf(Item()) }

    //variabile per controllo del popup
    var openDialog by remember { mutableStateOf(false)  }

    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg_green),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ParameterBars(players!![vm.myPlayerId]!!.house.stats,0.15f)

            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f),
                /*.padding(1.dp),*/
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                //RoundCard(numeroTurno.value, "2")
                RoundCard("${gameInfos!!.roundCounter+1}/${gameInfos!!.totalRounds}",
                    "${gameInfos!!.turnCounter+1}/${gameInfos!!.totalTurns}")
                Spacer(modifier = Modifier.weight(1f))

                MyTimerCard(gameInfos!!.turnTime){
                    //TODO: End Turn
                    vm.onEndTurnButtonPressed()
                }
                Spacer(modifier = Modifier.weight(1f))

                MoneyCard(players!![vm.myPlayerId]!!.wallet.coins)
            }

            if (houseVisibility){
                HouseViewBox(players!![vm.myPlayerId]!!.house.items)
            } else{
                if(shopVisibility){
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        //lista di oggetti nello shop
                        MyItemListCard(itemList = shop!!.values.toList(),
                            enabledCardClick = true,
                            onClickEvent = {
                                selectedItem = it
                                openDialog = !openDialog
                            })
                    }
                } else {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        //lista di oggetti acquistati
                        MyItemListCard(itemList = players!![vm.myPlayerId]!!.house.items, false) {}
                    }
                }
            }

            Spacer(modifier = Modifier
                .weight(0.1f))

            //button per mostrare oggetti acquistati
            MyButton(title = if(houseVisibility) "ITEM LIST VIEW" else "HOUSE VIEW", description = "View item list button", 70) {
                houseVisibility = !houseVisibility
                shopVisibility = false
            }
            Spacer(modifier = Modifier
                .weight(0.05f))

            if(disableButton){
                //button per mostrare negozio
                MyButton(title = if (shopVisibility) "BACK" else "SHOP",
                    description = "Shop button", 70) {
                    houseVisibility = false
                    shopVisibility = !shopVisibility
                }
                Spacer(modifier = Modifier
                    .weight(0.05f))
            }

            //button per terminare il turno
            MyButton(title = "END TURN", description = "End turn button", 50) {
                /*TODO: passare funzione per terminare il turno*/
                vm.onEndTurnButtonPressed()
            }

            //alert per conferma di acquisto
            if(openDialog){
                MyAlertDialog(
                    item = selectedItem,
                    dismissAlert = {openDialog = !openDialog},
                    canBuy = selectedItem.price <= players!![vm.myPlayerId]!!.wallet.coins,
                    buyItem = {
                        openDialog = !openDialog //disabilita Alert
                        disableButton = false //disabilita button dello shop
                        houseVisibility = true //abilita vista casa
                        shopVisibility = false // disabilita vista shop
                        Log.d("PROVA", "PROVA LOG")
                    /*TODO: passare funzione per aggiungere oggetto salvato in selectedItem a lista acquistati*/
                        vm.onBuyButtonPressed(selectedItem.id)
                    })
            }
        }
    }
}

@Composable
fun HouseViewBox(ownedItems: ArrayList<Item>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    ) {
        //immagine base della casa vuota
        Image(painter = painterResource(R.drawable.house_int),
            contentDescription = "house",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )

        ownedItems.forEachIndexed { _, item ->
            Image(painter = rememberImagePainter(data = item.houseSpriteURL),
                contentDescription = "sprite_description",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
        }

        //IMMAGINI DI PROVA
        /*
        Image(painter = painterResource(R.drawable.shower_int),
            contentDescription = "house",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Image(painter = painterResource(R.drawable.wind_turbine_int),
            contentDescription = "house",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
         */
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HouseOverviewScreenPreview() {
    HouseOverviewScreen(GameViewModel())
}