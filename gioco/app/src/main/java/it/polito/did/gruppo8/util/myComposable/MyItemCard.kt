package it.polito.did.gruppo8.util.myComposable

import android.util.Log
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Item
import it.polito.did.gruppo8.model.baseClasses.StatModifier
import it.polito.did.gruppo8.screens.caveatSemiBold

/***
 * Generalized card with name of the item, item image, price, influence on parameters
 *
 * @param item item to show in the card
 * @param onClickEvent the function that activates when the card is pressed
 * @param enabled by default on false, set the button clickability
 *
 */

//Occorre passargli valore dell'oggetto preso dal database, descrizione e immagine
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyItemCard(
    item: Item,
    enabled: Boolean = false,
    onClickEvent: () -> Unit
    /*title:String,
    itemImage: Painter,
    price: String,
    paramInfluenceImageList: List<Painter>*/
) {

    Button(
        onClick = onClickEvent,
        enabled = enabled,
        modifier = Modifier
            .width(175.dp)
            .fillMaxHeight(),
        colors = ButtonDefaults
            .buttonColors(backgroundColor = colorResource(id = R.color.white),
            ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.DarkGray)
    ){
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = item.name,
                color = Color.Black,
                fontFamily = caveatSemiBold,
                style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = item.iconURL,
                contentDescription = item.name,
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
                    contentScale = FillBounds
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = item.price.toString(),
                    color = Color.Black,
                    fontFamily = caveatSemiBold,
                    style = MaterialTheme.typography.body1)

                Spacer(modifier = Modifier.weight(1f))

                Column(modifier = Modifier
                    .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //modificatore parametro comfy
                    if(item.comfyModifier == StatModifier.BestImpact){
                        Image(painter = painterResource(id = R.drawable.double_up),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    } else if (item.comfyModifier == StatModifier.GoodImpact){
                        Image(painter = painterResource(id = R.drawable.up),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    } else if (item.comfyModifier == StatModifier.NeutralImpact){
                        Image(painter = painterResource(id = R.drawable.equal),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    } else if (item.comfyModifier == StatModifier.BadImpact){
                        Image(painter = painterResource(id = R.drawable.down),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    } else if (item.comfyModifier == StatModifier.WorstImpact){
                        Image(painter = painterResource(id = R.drawable.double_down),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Image(painter = painterResource(id = R.drawable.sofa),
                        contentDescription = "sofa",
                        modifier = Modifier,
                        contentScale = FillBounds
                    )
                }
                Spacer(modifier= Modifier.weight(0.3f))
                Column(modifier = Modifier
                    .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    //parametro ecologia
                    if(item.greenModifier == StatModifier.BestImpact){
                        Image(painter = painterResource(id = R.drawable.double_up),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    } else if (item.greenModifier == StatModifier.GoodImpact){
                        Image(painter = painterResource(id = R.drawable.up),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    } else if (item.greenModifier == StatModifier.NeutralImpact){
                        Image(painter = painterResource(id = R.drawable.equal),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    } else if (item.greenModifier == StatModifier.BadImpact){
                        Image(painter = painterResource(id = R.drawable.down),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    } else if (item.greenModifier == StatModifier.WorstImpact){
                        Image(painter = painterResource(id = R.drawable.double_down),
                            contentDescription = "double arrow up",
                            modifier = Modifier,
                            contentScale = FillBounds
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    Image(painter = painterResource(id = R.drawable.leaf),
                        contentDescription = "leaf",
                        modifier = Modifier,
                        contentScale = FillBounds
                    )
                }
            }
        }
    }
}