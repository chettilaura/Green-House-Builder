package it.polito.did.gruppo8.screens.NewScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.FormCard
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.screens.caveatRegular
import it.polito.did.gruppo8.screens.caveatSemiBold
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme


@Composable
fun GameStartScreen(modifier: Modifier) {

    var nPlayers by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )

        GameStartTopBar(title = "NEW GAME", colorId = colorResource(id = R.color.cal_poly_green))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(30.dp))
            NewGameButton("NEW GAME", "Create house button")
            Spacer(modifier = Modifier.size(10.dp))
            NPlayers ("players", "players", nPlayers, 0.3f)
            Spacer(modifier = Modifier.size(10.dp))
            FormCard(title = "Game ID", label = "Id number", fieldValue = nPlayers , fraction = 0.3f)
            Spacer(modifier = Modifier.size(30.dp))

//TUTORIAL POP UP

            var SetGamePopUpControl by remember { mutableStateOf(false) }
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = Color.Transparent
            ) {
                Image(
                    painter = painterResource(R.drawable.empty_button),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { SetGamePopUpControl = true }) {
                        Text(
                            "GAME SETUP",
                            fontFamily = caveatBold,
                            color = Color.White,
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(5.dp))

            if (SetGamePopUpControl) {

                Popup(
                    alignment = Center,
                    onDismissRequest = { SetGamePopUpControl = false }) {
                    SetUpPopUp()
                }
            }

            StartGameButton(title = "START", description = "start game button")

        }

    }
}

//COMPOSABLES FUNCTIONS

@Composable
fun GameStartTopBar(title: String, colorId: Color) {
    TopAppBar (
        modifier = Modifier.height(100.dp),
        title = {
            Text(title,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h2)
        },
        navigationIcon = {},
        backgroundColor = colorId,
        elevation = 40.dp
    )
}

@Composable
public fun NPlayers (title: String, label: String, fieldValue: TextFieldValue, fraction:Float) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp),
        backgroundColor = colorResource(id = R.color.emerald),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Black)) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = title,
                fontFamily = caveatSemiBold,
                color = Color.White,
                style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.size(4.dp))

            var fieldValue by remember { mutableStateOf(TextFieldValue(""))}
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    /*.background(colorResource(id = emerald))*/
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.cal_poly_green),
                        shape = RoundedCornerShape(10.dp)
                    ),
                value = fieldValue,
                onValueChange = { fieldValue = it },
                label = {
                    Text(text = label,
                        color = Color.White,
                        fontFamily = caveatRegular,
                        style = MaterialTheme.typography.body1
                    )},
                /*colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = asparagus)
                )*/
            )
        }
    }
}

@Composable
fun SetUpPopUp() {
    val shape = RoundedCornerShape(30.dp)
    var tofill by remember { mutableStateOf(TextFieldValue("")) }
    Box(
        modifier = Modifier
            .size(400.dp, 400.dp)
            .clip(shape)
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.tut_background),
                contentDescription = "tutorial background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NPlayers(title ="max players" , label = "players", fieldValue = tofill, fraction = 0.3f )
                    NPlayers(title ="turns" , label = "players", fieldValue = tofill, fraction = 0.3f )
                    NPlayers(title ="time" , label = "players", fieldValue = tofill, fraction = 0.3f )
                }


        }

    }
}


@Composable
fun NewGameButton(title: String, description: String) {
    Box(modifier = Modifier
        .height(100.dp)
    ){
        Image(
            painter = painterResource(R.drawable.name_of_the_city_tabs),
            contentDescription = description,
            Modifier.fillMaxSize()
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
                Text("CITY NAME" , //DA PASSARE DA SCHERMATA NEW GAME SCREEN
                    fontFamily = caveatBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h4)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun StartGameButton(title: String, description: String) {
    Box(modifier = Modifier
        .height(100.dp)
    ){
        Image(
            painter = painterResource(R.drawable.empty_button),
            contentDescription = description,
            Modifier.fillMaxSize().clickable {  }
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
                Text(title , //DA PASSARE DA SCHERMATA NEW GAME SCREEN
                    fontFamily = caveatBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h4)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}


/*--------------------------------------------------------------------*/
//PREVIEW

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGameStartScreen() {
    GameSkeletonTheme {
        GameStartScreen(modifier = Modifier)
    }
}

