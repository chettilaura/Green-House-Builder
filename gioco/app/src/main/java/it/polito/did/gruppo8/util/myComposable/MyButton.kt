package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatBold

/***
 * Generalized Button component.
 *
 * @param title text showed on the Button.
 * @param description short description of the component.
 * @param onClickEvent action to trigger when the button is pressed.
 */
@Composable
fun MyButton(title: String,
             description: String,
             onClickEvent: () -> Unit)
{
    Box(modifier = Modifier
        .height(100.dp)
    ){
        Image(
            painter = painterResource(R.drawable.empty_button),
            contentDescription = description,
            modifier = Modifier
                //.clickable { onJoinGame(gameID, playerName) }
                .clickable(onClick = onClickEvent)
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(title,
                    fontFamily = caveatBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h4)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}