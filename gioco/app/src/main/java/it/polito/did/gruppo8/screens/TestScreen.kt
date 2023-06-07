package it.polito.did.gruppo8.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.util.myComposable.MyFormLine
import it.polito.did.gruppo8.util.myComposable.MyParameterBarCardPreview

/*TODO: Schermata di test per provare elementi grafici*/
/*TODO: serve per testare composable, si può eliminare se funziona tutto*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestScreen() {
    /*val houses = remember { mutableStateListOf<String>("Alice", "Bob", "Charlie", "David", "Emma",
            "Frank", "Grace", "Henry", "Ivy", "Jack",
            "Kate", "Liam", "Mia", "Noah", "Olivia")
    }*/

    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /*MyLazyColumnListCard("PLAYERS", houses)*/

            /*Button(onClick = { houses.add(0,"newname") }, ) {
                Text(text = "ADD")
            }*/
            var target by remember{mutableStateOf("")}
            MyFormLine(title = "Titolo", label = "label", targetValue = target, updateTargetCallback = {value -> target = value})
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestScreenPreview() {
    TestScreen()
}