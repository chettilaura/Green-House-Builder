package it.polito.did.gruppo8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.polito.did.gruppo8.screens.MainScreen
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

//classe lanciata all'avvio -> definita nel manifest file come "android:name=".MainActivity
//questa classe eredita le funzioni di Activity -> lancia Activity con onCreate
//carica il primo screen (che non è un vero screen bensì è un loader degli altri screen -> infatti non compare in screenName)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //"fun GameSkeletonTheme" definita in "Theme.kt"
            GameSkeletonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Welcome(){
    Text("Hello")
}

