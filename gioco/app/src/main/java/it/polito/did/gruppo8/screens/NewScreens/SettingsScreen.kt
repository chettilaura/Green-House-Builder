package it.polito.did.gruppo8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

//Schermata per le impostazioni iniziali dell'applicazione
//contiene button per tornare indietro, dropdown menu per la lingua, switch per attivare/disattivare sfx
//switch per vibrazione on/off
//button per credits

@Composable
fun SettingsScreen(modifier: Modifier = Modifier){
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )

        Box(modifier = Modifier.fillMaxSize(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsTopBar(
                    title = "SETTINGS",
                    colorId = colorResource(id = R.color.cal_poly_green)
                )

                Column(modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Row(){
                        Text("MUSIC",
                            fontFamily = caveatBold,
                            color = Color.Black,
                            style = MaterialTheme.typography.h4)
                        SwitchDemo()

                    }
                    Row(){
                        Text("SFX",
                            fontFamily = caveatBold,
                            color = Color.Black,
                            style = MaterialTheme.typography.h4)
                        SwitchDemo()

                    }
                    Row(){
                        Text("VIBRATION",
                            fontFamily = caveatBold,
                            color = Color.Black,
                            style = MaterialTheme.typography.h4)
                        SwitchDemo()

                    }
                }


            }

        }
    }
}

/*------------------------COMPOSABLES-------------------------------------*/

@Composable
fun SettingsTopBar(title: String, colorId: Color) {
    TopAppBar (
        modifier = Modifier.height(80.dp),
        title = {
            Text(title,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h4)
        },
        elevation = 20.dp,
        backgroundColor = colorId
    )
}

@Composable
fun SwitchDemo() {
    val checkedState = remember { mutableStateOf(true) }
    Switch(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}


/*--------------------------PREVIEW-----------------------------------*/

@Preview (showSystemUi = true, showBackground = true)
@Composable
fun PreviewSettingsScreen(){
    GameSkeletonTheme{
        SettingsScreen()
    }
}