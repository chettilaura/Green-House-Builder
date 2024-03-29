import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
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
import it.polito.did.gruppo8.model.baseClasses.Item
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.screens.caveatSemiBold
import it.polito.did.gruppo8.util.myComposable.MyButton

@Composable
fun MyAlertDialog(item: Item, dismissAlert: () -> Unit, canBuy: Boolean=true, buyItem: () -> Unit) {
    AlertDialog(
        onDismissRequest = dismissAlert,
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        title = {
            Text(
                text = item.name,
                color = Color.Black,
                fontFamily = caveatBold,
                style = MaterialTheme.typography.h4) },
        text = {
            if(canBuy){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "DO YOU WANT TO BUY IT FOR",
                        color = Color.Black,
                        fontFamily = caveatSemiBold,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = item.price.toString() + " ",
                            color = Color.Black,
                            fontFamily = caveatBold,
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                        Image(painter = painterResource(id = R.drawable.coin),
                            contentDescription = "price",
                            modifier = Modifier,
                            contentScale = ContentScale.FillBounds
                        )
                        Text(text = "?",
                            color = Color.Black,
                            fontFamily = caveatSemiBold,
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center)
                    }
                }
            } else{
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "YOU DON'T HAVE ENOUGH MONEY!",
                        color = Color.Black,
                        fontFamily = caveatSemiBold,
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = item.price.toString() + " ",
                            color = colorResource(id = R.color.cordovan),
                            fontFamily = caveatBold,
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                        Image(painter = painterResource(id = R.drawable.coin),
                            contentDescription = "price",
                            modifier = Modifier,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }
        },
        confirmButton = {
            if(canBuy){
                MyButton(title = "BUY", description = "Buy button", buttonHeight = 50,  enabled = canBuy, onClickEvent = buyItem)
            }
        },
        dismissButton = {
            MyButton(title = if(canBuy) "CANCEL" else "BACK", description = "Dismiss button", buttonHeight = 50, onClickEvent = dismissAlert)
        },

        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.White
    )
}

@Preview
@Composable
fun MyAlertDialogPreview() {
    MyAlertDialog(item = Item(), dismissAlert = { /*TODO*/ }, canBuy = false) {
        
    }
}