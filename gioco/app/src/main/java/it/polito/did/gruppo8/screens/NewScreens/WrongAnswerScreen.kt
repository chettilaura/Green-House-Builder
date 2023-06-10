package it.polito.did.gruppo8.screens.NewScreens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatBold
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme


@Composable
fun WrongAnswerScreen(vm: GameViewModel, modifier: Modifier = Modifier) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Box(modifier = Modifier.fillMaxSize(1f)){
        Image(
            painter = painterResource(R.drawable.bg_red),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column() {


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
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.wrong, imageLoader),
                        contentDescription = null
                    )
                    Box {
                        //TODO: mettere background rosso

                        TextShadowWrong()
//                        Text(text = "WRONG ANSWER !",
//                            fontFamily = caveatBold,
//                            color = Color.Red,
//                            style = MaterialTheme.typography.h3
//                        )
                    }

                    Spacer(modifier = Modifier.size(32.dp))

                    //TODO: Sarebbe carino se al posto di COINS ci fosse l'immagine della moneta

                    // Se è il turno del giocatore corrente
                    if(vm.myPlayerId==vm.gameInfos.value!!.currentPlayerId){
                        Text(text = "DON'T GIVE UP!\nWAIT FOR THE NEXT TURN",
                            fontFamily = caveatBold,
                            color = Color.Black,
                            style = MaterialTheme.typography.h4
                        )
                    }
                    // Altrimenti
                    else{
                        Text(text = "YOU LOST 25 COINS!\nDON'T GIVE UP," +
                                "WAIT FOR THE NEXT TURN",
                            fontFamily = caveatBold,
                            color = Color.Black,
                            style = MaterialTheme.typography.h4
                        )
                    }
                    /*
                    Text(text = "DON'T GIVE UP, \nWAIT FOR THE NEXT TURN",
                        fontFamily = caveatBold,
                        color = Color.Black,
                        style = MaterialTheme.typography.h4
                    )
                     */
                }


            }
        }
    }
}


@Composable //da aggiungere in util.MyComposable
fun TextShadowWrong() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "WRONG!",
        fontFamily = caveatBold,
        style = TextStyle(
            fontSize = 60.sp,
            shadow = Shadow(
                color = Color(0xFFED5C4F), offset = offset, blurRadius = 3f
            )
        )
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WrongAnswerPreview(modifier: Modifier = Modifier) {
    //val vm = GameViewModel()
    GameSkeletonTheme {
        WrongAnswerScreen(GameViewModel())
    }
}


