package it.polito.did.gruppo8.screensGio

import android.widget.Switch
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.polito.did.gruppo8.R
import org.intellij.lang.annotations.Language

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreen(){
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.background_purple),
            contentDescription = "SettingsScreen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .background(color = Color.Black)
        )
        {
            SettingsBackButton()
            SettingsTitle()
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
            ){
            Language()
            Music()
            Vibration()

        }

    }
}


@Composable
fun SettingsTitle()
{
            Text(
                "SETTINGS",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
}

@Composable
fun SettingsBackButton() {
    val context = LocalContext.current
    IconButton(

        onClick = {
            Toast.makeText(context, "Clicked on back button", Toast.LENGTH_SHORT).show()
        }
    )
    {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "Back Button",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp),
        )
    }
}

@Composable
fun Language()  //da modificare per permettere selezione lingue (no switch)
{

    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically)
    {
        Text("Language",
            textAlign = TextAlign.Start,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White)

        val checkedState = remember{
            mutableStateOf(false)
        }

        Switch(
            checked = checkedState.value ,
            onCheckedChange = {checkedState.value = it},
            colors = SwitchDefaults.colors(Color.Green),
            modifier = Modifier
                .size(50.dp)
        )

    }
}

@Composable
fun Music(){

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Music",
            textAlign = TextAlign.Start,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        val checkedState = remember {
            mutableStateOf(false)
        }

        Switch(
            checked = checkedState.value ,
            onCheckedChange = {checkedState.value = it},
            colors = SwitchDefaults.colors(Color.Green),
            modifier = Modifier
                .size(50.dp)
        )

    }
}

@Composable
fun Vibration(){

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Vibration",
            textAlign = TextAlign.Start,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        val checkedState = remember {
            mutableStateOf(false)
        }

        Switch(
            checked = checkedState.value ,
            onCheckedChange = {checkedState.value = it},
            colors = SwitchDefaults.colors(Color.Green),
            modifier = Modifier
                .size(50.dp)
        )

    }
}

//@Composable
//fun ApplyButton(){   DA MODIFICARE
//    val context = LocalContext.current
//    OutlinedButton(onClick = { //cancellare riga Toast sotto e mettere cosa succede se clicco
//        Toast.makeText(context,"Clicked on Apply", Toast.LENGTH_SHORT).show()
//    },  shape = CircleShape,
//        contentPadding = PaddingValues(10.dp),
//        border = BorderStroke(5.dp, color = Color.White),
//        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black,
//            contentColor = Color.White)
//    )
//    {
//        Text("Apply",
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.h3,
//            modifier = Modifier
//                .padding(5.dp)
//                .fillMaxWidth()
//        )
//    }
//}


