package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

/*TODO: da eliminare*/
@Composable
fun PlayerScreen(modifier: Modifier = Modifier) {
    GenericScreen(title = "Player", modifier) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerScreen() {
    GameSkeletonTheme {
        PlayerScreen()
    }
}