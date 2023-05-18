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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.util.myComposable.*
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import androidx.compose.animation.core.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import it.polito.did.gruppo8.screens.caveatBold


@Composable
fun NoAnswerScreen(modifier: Modifier = Modifier) {

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
            painter = painterResource(R.drawable.bg),
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
                        painter = rememberAsyncImagePainter(R.drawable.question_mark_gif, imageLoader),
                        contentDescription = null
                    )
                    Box {

                        TextShadowNoAnswer()
//                        Text(text = "WRONG ANSWER !",
//                            fontFamily = caveatBold,
//                            color = Color.Red,
//                            style = MaterialTheme.typography.h3
//                        )
                    }

                    Spacer(modifier = Modifier.size(32.dp))

                    Text(text = "DON'T GIVE UP, \nWAIT FOR THE NEXT TURN",
                        fontFamily = caveatBold,
                        color = Color.Black,
                        style = MaterialTheme.typography.h4

                    )
                }


            }
        }
    }
}


@Composable //da aggiungere in util.MyComposable
fun TextShadowNoAnswer() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "YOU DID NOT ANSWER!",
        fontFamily = caveatBold,
        style = TextStyle(
            fontSize = 60.sp,
            shadow = Shadow(
                color = Color.Red, offset = offset, blurRadius = 3f
            )
        )
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoAnswerPreview(modifier: Modifier = Modifier) {
    //val vm = GameViewModel()
    GameSkeletonTheme {
        NoAnswerScreen()
    }
}


