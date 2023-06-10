package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.Navigator
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatBold

/***
 * Generalized Top Bar.
 *
 * @param title text showed on the component.
 * @param colorId background color.
 */
@Composable
fun MyTopBar(title: String, colorId: Color) {
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
                    .clickable { Navigator.back() }
            )
        },
        backgroundColor = colorId,
        elevation = 40.dp
    )
}