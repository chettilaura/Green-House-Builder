package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.model.baseClasses.Item

/***
 * Generalized Card with item list component.
 *
 * @param itemList list of item to show inside the card.
 * @param onClickEvent function to pass to the ItemCard when it's pressed
 * @param enabledCardClick boolean to set the ItemCard clickability
*
* */

@Composable
fun MyItemListCard(itemList: List<Item>,
                   enabledCardClick: Boolean,
                   /*openDialog: Boolean,*/
                   onClickEvent: (Item) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        backgroundColor = colorResource(id = R.color.emerald),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        border = BorderStroke(2.dp, Color.Black)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(2.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
            items(itemList) { item ->
                MyItemCard(item, enabledCardClick) { onClickEvent(item) }
            }
        }
    }
}