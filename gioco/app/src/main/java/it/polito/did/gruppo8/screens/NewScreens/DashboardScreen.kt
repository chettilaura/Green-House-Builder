package it.polito.did.gruppo8.screens.NewScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import it.polito.did.gruppo8.model.baseClasses.Player
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.util.myComposable.MyPlayerData

//SCHERMATA CHE VEDE SOLO L'HOST

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {

    val players = listOf<Player>()
    //val players by vm.players!!.observaAsState()

    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            DashBoardTopBar(title = "PLAYERS ", colorId = colorResource(id = R.color.cal_poly_green))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                MyPlayerData(players) //passare i giocatori e relativi parametri a funzione originale
            }
        }
    }
}

@Composable
fun DashBoardTopBar(title: String, colorId: Color) {
    TopAppBar (
        modifier = Modifier.height(100.dp),
        title = {
            Text(title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h2)

        },
        backgroundColor = colorId,
        elevation = 40.dp
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(modifier: Modifier = Modifier) {
    DashboardScreen()
}