package it.polito.did.gruppo8.screens.NewScreens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.util.myComposable.*
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.platform.LocalDensity
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.Navigator
import it.polito.did.gruppo8.ScreenName
import it.polito.did.gruppo8.screens.caveatBold
import kotlinx.coroutines.delay


@Composable
fun NewGameScreen(vm:GameViewModel, modifier: Modifier = Modifier) {



    var cityName by remember { mutableStateOf("") }
    var showLoading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(1f)){

        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        MyTopBar(title = "NEW GAME", colorId = colorResource(id = R.color.cal_poly_green))




        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyFormBox ("CITY NAME", "NAME", cityName, {cityName = it},0.3f) // call a JoinGameScreen

            Spacer(modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.size(15.dp))

            MyButton(title = "CREATE CITY", description = "Create City", 100, {vm.onCreateCityButtonPressed(cityName)})

            Spacer(modifier = Modifier.size(50.dp))

            if (showLoading) {
                LoadingAnimation()
            }
        }
    }
}


/* PREVIEW ---------------------------------------------------- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewGameScreen(modifier: Modifier = Modifier) {
    val vm = GameViewModel()
    GameSkeletonTheme {
        NewGameScreen(vm,modifier = Modifier)
    }
}

@Preview
@Composable
fun PreviewLoadingAnimation(modifier: Modifier = Modifier) {
    LoadingAnimation()
}

