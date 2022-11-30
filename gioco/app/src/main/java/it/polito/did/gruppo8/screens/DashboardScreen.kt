package it.polito.did.gruppo8.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    GenericScreen(title = "Dashboard", modifier) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen() {
    GameSkeletonTheme {
        DashboardScreen()
    }
}