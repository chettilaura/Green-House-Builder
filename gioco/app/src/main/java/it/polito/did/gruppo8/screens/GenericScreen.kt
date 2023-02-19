package it.polito.did.gruppo8.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

@Composable
fun GenericScreen( title: String, modifier: Modifier = Modifier, content: @Composable() () -> Unit = {} )
{
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .then(modifier)
    ) {padding ->
        Column(Modifier.padding(padding)) {
            Text(
                title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Left
            )
            content()
        }
    }
}

//usa previewGenericScreen per fare preview locale
@Preview(showBackground = true)
@Composable
fun PreviewGenericScreen() {
    GameSkeletonTheme {
        GenericScreen(title = "Generic Screen")
    }

}