package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatSemiBold

/***
 * Generalized card with name of the item, item image, price, influence on parameters
 *
 *
 * @param title text showing name of the item
 * @param itemImage painter containing the image of the item
 * @param price text showing the price of the item
 * @param paramInfluenceImageList list of painter containing showing item influence on parameters
 *
 *
 */

//Occorre passargli valore dell'oggetto preso dal database, descrizione e immagine
@Composable
fun ItemCard(title:String,
             itemImage: Painter,
             price: String,
             paramInfluenceImageList: List<Painter>) {
    Card(
        modifier = Modifier
            .width(175.dp)
            .fillMaxHeight(),
        backgroundColor = colorResource(id = R.color.white),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        border = BorderStroke(2.dp, Color.DarkGray)
    ){
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title,
                color = Color.Black,
                fontFamily = caveatSemiBold,
                style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))

            Image(painter = itemImage,
                contentDescription = title,
                modifier = Modifier,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.coin),
                    contentDescription = "price",
                    modifier = Modifier,
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = price,
                    color = Color.Black,
                    fontFamily = caveatSemiBold,
                    style = MaterialTheme.typography.body1)

                Spacer(modifier = Modifier.weight(1f))

                Column(modifier = Modifier
                    .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = paramInfluenceImageList[1],
                        contentDescription = "arrow up",
                        modifier = Modifier,
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Image(painter = paramInfluenceImageList[0],
                        contentDescription = "sofa",
                        modifier = Modifier,
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier= Modifier.weight(0.3f))
                Column(modifier = Modifier
                    .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = paramInfluenceImageList[3],
                        contentDescription = "arrow down",
                        modifier = Modifier,
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Image(painter = paramInfluenceImageList[2],
                        contentDescription = "leaf",
                        modifier = Modifier,
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}