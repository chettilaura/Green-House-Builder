package it.polito.did.gruppo8.screens.NewScreens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.FormCard
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun NewGameScreen(onCreateCityButtonPressed: (String)->Unit, modifier: Modifier) {

    var cityName by remember { mutableStateOf(TextFieldValue("")) }
    var showLoading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(1f)){

        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        NewGameTopBar(title = "NEW GAME", colorId = colorResource(id = R.color.cal_poly_green))
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormCard ("CITY NAME", "NAME", cityName, 0.3f) // call a JoinGameScreen
            Spacer(modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.size(15.dp))
            //NewGameButton ("CREATE CITY", "Create city button", cityName)
            //Spacer(modifier = Modifier.size(8.dp))

            Box(modifier = Modifier
                .height(100.dp)
            ){
                Image(
                    painter = painterResource(R.drawable.empty_button),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { }
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showLoading = true; onCreateCityButtonPressed(cityName.text) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .padding(0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("CREATE CITY",
                            fontFamily = caveatBold,
                            color = Color.White,
                            style = MaterialTheme.typography.h4)
                    }
                    Spacer(modifier = Modifier.size(10.dp))

                }
            }

            Spacer(modifier = Modifier.size(50.dp))
            if (showLoading) {
                LoadingAnimation()
            }
        }
    }
//    Column(
//        modifier = Modifier
//            .fillMaxHeight()
//            .padding(8.dp),
//        verticalArrangement = Arrangement.Bottom, Alignment.CenterHorizontally
//    ) {
//
//        Spacer(modifier = Modifier.size(15.dp))
//        NewGameButton ("CREATE CITY", "Create city button", cityName)
//        Spacer(modifier = Modifier.size(8.dp))
//    }


}

/* COMPOSABLES---------------------------------------------------- */

@Composable
fun NewGameTopBar(title: String, colorId: Color) {
    TopAppBar (
        modifier = Modifier.height(100.dp),
        title = {
            Text(title,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h2)
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back button",
                modifier = Modifier
                    .padding(3.dp)
                    .clickable { /*TODO: tornare a schermata OpenScreen*/ }
            )
        },
        backgroundColor = colorId,
        elevation = 40.dp
    )
}

//@Composable
//fun NewGameButton(
//    title: String,
//    description: String,
//    cityName: TextFieldValue
//) {
//    var showLoading by remember { mutableStateOf(false) }
//    Box(modifier = Modifier
//        .height(100.dp)
//    ){
//        Image(
//            painter = painterResource(R.drawable.empty_button),
//            contentDescription = description,
//            modifier = Modifier
//                .clickable { }
//        )
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .clickable { showLoading = true },
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Row(
//                modifier = Modifier
//                    .padding(0.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(title,
//                    fontFamily = caveatBold,
//                    color = Color.White,
//                    style = MaterialTheme.typography.h4)
//            }
//            Spacer(modifier = Modifier.size(10.dp))
//
//        }
//    }
//
//}

/* LOADING ---------------------------------------------------- */

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = Color(0xFF599C68),
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circleValues.forEach { value ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = circleColor,
                        shape = CircleShape
                    )
            )
        }
    }

}


/* PREVIEW ---------------------------------------------------- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewGameScreen(modifier: Modifier = Modifier) {
    GameSkeletonTheme {
        NewGameScreen({}, modifier = Modifier)
    }
}

@Preview
@Composable
fun PreviewLoadingAnimation(modifier: Modifier = Modifier) {
    LoadingAnimation()
}

