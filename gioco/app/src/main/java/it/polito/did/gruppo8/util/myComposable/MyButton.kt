package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme


/***
 * Generalized Button component.
 *
 * @param title text showed on the Button.
 * @param description short description of the component.
 * @param buttonHeight sets the height of the component.
 * @param onClickEvent action to trigger when the button is pressed.
 */
@Composable
fun MyButton(title: String,
             description: String,
             buttonHeight: Int,
             enabled: Boolean = true,
             onClickEvent: () -> Unit
)
{
    Box(modifier = Modifier
        .height(buttonHeight.dp)
        .fillMaxWidth()
    ){
        if(enabled){
            Image(
                painter = painterResource(R.drawable.empty_button),
                contentDescription = description,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    //.clickable { onJoinGame(gameID, playerName) }
                    .clickable(
                        enabled = enabled,
                        onClick = onClickEvent)
            )
        } else{
            Image(
                painter = painterResource(R.drawable.empty_button_disabled),
                contentDescription = description,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    //.clickable { onJoinGame(gameID, playerName) }
                    .clickable(
                        enabled = enabled,
                        onClick = onClickEvent)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(title,
                    fontFamily = caveatBold,
                    color = if(enabled) Color.White else Color.DarkGray,
                    style = MaterialTheme.typography.h4)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}
