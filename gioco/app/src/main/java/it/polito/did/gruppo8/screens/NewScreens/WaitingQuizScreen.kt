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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewParameter
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.Navigator
import it.polito.did.gruppo8.ScreenName
import it.polito.did.gruppo8.screens.caveatBold
import kotlinx.coroutines.delay


@Composable
fun WaitingQuizScreen(vm: GameViewModel, modifier: Modifier = Modifier) {

    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column() {

            QuizTopBar(
                title = "It's 'player n. x' turn",
                colorId = colorResource(id = R.color.cal_poly_green)
            )
            //TODO: passare il numero / nickname del giocatore alla QuizTopBar, modificata perchè non presente bottone di back ma comunque
            // possibile metterla in util.MyComposable

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // TODO: mettere animazione loading

                    Card(){

                        Column() {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "if it's you...")
                            }

                            Box(modifier = Modifier) {
                                Image(painter = painterResource(id = R.drawable.empty_button)
                                    ,contentDescription = "infoField" )
                                Column(modifier = Modifier.padding(10.dp).fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "right answer")
                                    Text(text = "wrong answer")
                                    Text(text = "no answer")
                                }
                            }

                            Spacer(modifier = Modifier.size(32.dp))

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "if it's not you...")
                            }

                            Box(modifier = Modifier) {
                                Image(painter = painterResource(id = R.drawable.empty_button)
                                    ,contentDescription = "infoField" )
                                Column(modifier = Modifier.padding(10.dp).fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "right answer")
                                    Text(text = "wrong answer")
                                    Text(text = "no answer")
                                }
                            }

                        }


                    }
                }


            }
        }
      }
    }


/* LOADING ---------------------------------------------------- */

// Valutare se ha senso generalizzare e spostare questo composable nel package util.myComposable
// -Mattia
//@Composable
//fun LoadingAnimation(
//    modifier: Modifier = Modifier,
//    circleSize: Dp = 25.dp,
//    circleColor: Color = Color(0xFF599C68),
//    spaceBetween: Dp = 10.dp,
//    travelDistance: Dp = 20.dp
//) {
//    val circles = listOf(
//        remember { Animatable(initialValue = 0f) },
//        remember { Animatable(initialValue = 0f) },
//        remember { Animatable(initialValue = 0f) }
//    )
//
//    circles.forEachIndexed { index, animatable ->
//        LaunchedEffect(key1 = animatable) {
//            delay(index * 100L)
//            animatable.animateTo(
//                targetValue = 1f,
//                animationSpec = infiniteRepeatable(
//                    animation = keyframes {
//                        durationMillis = 1200
//                        0.0f at 0 with LinearOutSlowInEasing
//                        1.0f at 300 with LinearOutSlowInEasing
//                        0.0f at 600 with LinearOutSlowInEasing
//                        0.0f at 1200 with LinearOutSlowInEasing
//                    },
//                    repeatMode = RepeatMode.Restart
//                )
//            )
//        }
//    }
//
//    val circleValues = circles.map { it.value }
//    val distance = with(LocalDensity.current) { travelDistance.toPx() }
//
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
//    ) {
//        circleValues.forEach { value ->
//            Box(
//                modifier = Modifier
//                    .size(circleSize)
//                    .graphicsLayer {
//                        translationY = -value * distance
//                    }
//                    .background(
//                        color = circleColor,
//                        shape = CircleShape
//                    )
//            )
//        }
//    }
//
//}

@Composable
fun QuizTopBar(title: String, colorId: Color) {
    TopAppBar (
        modifier = Modifier.height(100.dp),
        title = {
            Text(title,
                fontFamily = caveatBold,
                color = Color.White,
                style = MaterialTheme.typography.h3)
        },
        backgroundColor = colorId,
        elevation = 40.dp
    )
}


/* PREVIEW ---------------------------------------------------- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWaitingQuizScreen(modifier: Modifier = Modifier) {
    //val vm = GameViewModel()
    GameSkeletonTheme {
        WaitingQuizScreen(GameViewModel())
    }
}


