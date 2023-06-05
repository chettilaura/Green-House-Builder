package it.polito.did.gruppo8.screens

import MyAlertDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Item
import it.polito.did.gruppo8.util.myComposable.MyButton
import it.polito.did.gruppo8.util.myComposable.MyItemCard
import it.polito.did.gruppo8.util.myComposable.MyItemListCard
import it.polito.did.gruppo8.util.myComposable.MyTopBar

//contiene il negozio: quattro possibili card da scegliere con oggetti casuali
//deve essere presente anche indicazione sul turno, sul tempo rimanente per scegliere cosa fare, soldi rimanenti

fun showAlert(show: Boolean): Boolean{
    return !show
}

@Composable
fun ShopScreen(modifier: Modifier = Modifier) {

    //lista di 4 oggetti per test dell'interfaccia
    val itemList = listOf<Item>()

    //salva
    var selectedItem by remember { mutableStateOf(Item()) }


    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )        
        Column(
            modifier = Modifier.fillMaxSize(1f),
        ) {
            
            MyTopBar(title = "THE SHOP", colorId = colorResource(id = R.color.cal_poly_green))
            Spacer(Modifier.height(4.dp))

            Row(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {

                //booleano per visualizzazione di popup
                var openDialog by remember { mutableStateOf(true) }

                /*TODO: passare lista di oggetti da mostrare nel negozio*/
                MyItemListCard(itemList = itemList, enabledCardClick = true){
                    selectedItem = it
                    openDialog = !openDialog
                }
                if(openDialog){
                    MyAlertDialog(item = selectedItem,
                        dismissAlert = {openDialog = !openDialog},
                        buyItem = {/*TODO: passare funzione per aggiungere oggetto salvato in selectedItem a lista acquistati*/})
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShopScreenPreview() {
    ShopScreen()
}