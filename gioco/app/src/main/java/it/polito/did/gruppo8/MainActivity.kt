package it.polito.did.gruppo8

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
//import androidx.navigation.compose.rememberNavController
//import it.polito.did.gruppo8.screens.MainScreen
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme

//classe lanciata all'avvio -> definita nel manifest file come "android:name=".MainActivity
//questa classe eredita le funzioni di Activity -> lancia Activity con onCreate
//carica il primo screen (che non è un vero screen bensì è un loader degli altri screen -> infatti non compare in screenName)


class MainActivity : ComponentActivity() {

    private val nv = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val LocalFont = staticCompositionLocalOf<FontFamily?> { null }
            setContent {

                //"fun GameSkeletonTheme" definita in "Theme.kt"
                GameSkeletonTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        //MainScreen()
                        val navController = rememberNavController()
                        Navigation(navController,nv)
                    }
                }
            }

    }
}

