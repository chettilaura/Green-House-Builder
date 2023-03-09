package it.polito.did.gruppo8.screens

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel

//contiene il negozio: quattro possibili card da scegliere con oggetti casuali
//deve essere presente anche indicazione sul turno, sul tempo rimanente per scegliere cosa fare, soldi rimanenti


@Composable
fun ShopScreen(modifier: Modifier = Modifier) {
    GenericScreen(title = "Negozio", modifier){
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
                ) {
                    ItemCard(title = "oggetto 1")
                    Spacer(modifier = Modifier.size(8.dp))
                    ItemCard(title = "oggetto 2")
                    }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ItemCard(title = "oggetto 3")
                    Spacer(modifier = Modifier.size(8.dp))
                    ItemCard(title = "oggetto 4")
                }
            }
        }
    }

//TODO
}

//Occorre passargli valore dell'oggetto preso dal database, descrizione e immagine
@Composable
fun ItemCard(title:String) {
    Card(
        elevation = 5.dp,
        backgroundColor = Color.LightGray
    ){
      Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    ShopScreen()
}