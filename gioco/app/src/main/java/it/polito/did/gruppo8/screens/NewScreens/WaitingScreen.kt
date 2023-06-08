package it.polito.did.gruppo8.screens.NewScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImagePainter
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatSemiBold
import it.polito.did.gruppo8.util.myComposable.LoadingAnimation
import java.util.Locale

@Composable
fun WaitingScreen(
    vm: GameViewModel,
    topText: String,
    bottomText: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        /*MyTopBar(title = "WAITING", colorId = colorResource(id = R.color.cal_poly_green))*/
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text =topText,
                    color = Color.Black,
                    fontFamily = caveatSemiBold,
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.weight(1f))

            LoadingAnimation()
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = bottomText.uppercase(Locale.getDefault()),
                color = Color.Black,
                fontFamily = caveatSemiBold,
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))


        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun WaitingScreenPreview() {
    val vm = GameViewModel()
    WaitingScreen(vm, "TOP TEXT WAITING SCREEN", "BOTTOM TEXT WAITING SCREEN")
}
