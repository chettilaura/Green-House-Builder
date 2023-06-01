package it.polito.did.gruppo8.util.myComposable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayerList(){
    Column(modifier = Modifier
        .padding(15.dp)
        .verticalScroll(rememberScrollState())) {
        ParameterBars(0.1f,0.1f,0.1f,0.1f)
        Text(text = "Player1")
        ParameterBars(0.1f,0.1f,0.1f,0.1f)
        Text(text = "Player2")
        ParameterBars(0.1f,0.1f,0.1f,0.1f)
        Text(text = "Player3")
        ParameterBars(0.1f,0.1f,0.1f,0.1f)
        Text(text = "Player4")
        ParameterBars(0.1f,0.1f,0.1f,0.1f)
        Text(text = "Player5")
        ParameterBars(0.1f,0.1f,0.1f,0.1f)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewPlayerlist(){
    PlayerList()
}