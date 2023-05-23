package it.polito.did.gruppo8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import it.polito.did.gruppo8.util.myComposable.MyItemCard
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Item
import it.polito.did.gruppo8.util.myComposable.MyButton
import it.polito.did.gruppo8.util.myComposable.MyTopBar

//contiene il negozio: quattro possibili card da scegliere con oggetti casuali
//deve essere presente anche indicazione sul turno, sul tempo rimanente per scegliere cosa fare, soldi rimanenti



@Composable
fun ShopScreen(modifier: Modifier = Modifier) {

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
            Spacer(Modifier.height(8.dp))

            Row(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
                //TODO: passare lista di oggetti da mostrare nel negozio
               /* MyItemCardsList(itemList = )*/
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShopScreenPreview() {
    ShopScreen()
}