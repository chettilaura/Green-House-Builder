package it.polito.did.gruppo8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

@Composable
fun OpenScreen(onCreateNewGame:() -> Unit, onPreJoinGame:() -> Unit, modifier: Modifier = Modifier)
{
    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AppSettings()
            AppName()
            Tutorial()
            NewGame(onCreateNewGame)
            JoinGame(onPreJoinGame)
            Credits()

        }

    }
}

/*--------------------------------------------------------------------*/

@Composable
fun AppSettings()
{
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start) {
        Image(
            painter = painterResource(id = R.drawable.settings_button),
            contentDescription = null,
            modifier = Modifier.clickable {/*TODO:passare schermata impostazioni (o pop up)*/}
        )
    }
}

@Composable
fun AppName(){
    Row(horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.app_name),
            contentDescription = null,
        )
    }
}

@Composable
fun Tutorial()
{
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End) {
        Image(
            painter = painterResource(id = R.drawable.tutorial_button),
            contentDescription = null,
            modifier = Modifier.clickable {/*TODO:passare schermata tutorial (o pop up)*/}
        )
    }
}

@Composable
fun NewGame(onCreateNewGame: () -> Unit)
{
    Box() {
        Image(
            painter = painterResource(id = R.drawable.new_game_button),
            contentDescription = null,
            modifier = Modifier.clickable {onCreateNewGame()}
        )
    }
}

@Composable
fun JoinGame(onPreJoinGame: () -> Unit)
{
    Box() {
        Image(
            painter = painterResource(id = R.drawable.join_button),
            contentDescription = null,
            modifier = Modifier.clickable {onPreJoinGame()}
        )
    }
}

@Composable
fun Credits()
{
    Box() {
        Image(
            painter = painterResource(id = R.drawable.credits_button),
            contentDescription = null,
            modifier = Modifier.clickable {/*TODO:passare schermata credits*/}
        )
    }
}


/*--------------------------------------------------------------------*/
//PREVIEW SCHERMATA INIZIALE

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOpenScreen() {
    GameSkeletonTheme {
        OpenScreen({},{})
    }
}

