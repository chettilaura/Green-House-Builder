package it.polito.did.gruppo8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.icons.Splash
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import kotlinx.coroutines.CoroutineScope
/*TODO: da cambiare immagine della splash*/
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        )
        {
//        Image(
//            imageVector = Icons.Splash,
//            contentDescription = "Splash",
//            modifier = Modifier.align(Alignment.Center),
//        )

        Image(
            painter = painterResource(R.drawable.houseicon),
            contentDescription = "appIcon",
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            modifier = Modifier.size(100.dp,100.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 600, heightDp = 1280)
@Composable
fun PreviewSpashScreen() {
    GameSkeletonTheme {
        SplashScreen()
    }
}