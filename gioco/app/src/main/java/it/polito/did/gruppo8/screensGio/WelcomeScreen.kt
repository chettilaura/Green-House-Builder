package it.polito.did.gruppo8.screensGio

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.polito.did.gruppo8.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeSreen() {
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.background_purple),
            contentDescription = "Start",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .fillMaxHeight(0.6f))
        {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
            )
            {
                Setting()
                AppName()
                Tutorial()
            }
        }

        Box() {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                NewGame()
                Join()
                Credits()
            }
        }
    }
}



@Composable
fun NewGame(){
    val context = LocalContext.current
    OutlinedButton(onClick = { //cancellare riga Toast sotto e mettere cosa succede se clicco
        Toast.makeText(context,"Clicked on New Game", Toast.LENGTH_SHORT).show()
    },  shape = CircleShape,
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(5.dp, color = Color.White),
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent,
        contentColor = Color.White)
    )
    {
        Text("New Game",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
        )
    }
}

@Composable
fun Join(){
    val context = LocalContext.current
    OutlinedButton(onClick = {
        Toast.makeText(context,"Clicked on Join", Toast.LENGTH_SHORT).show()
    },  shape = CircleShape,
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(5.dp, color = Color.White),
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent,
        contentColor = Color.White)

    ){
        Text("Join game",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun Setting(){
    val context = LocalContext.current
    IconButton(onClick = {
        Toast.makeText(context,"Clicked on Settings",Toast.LENGTH_SHORT).show()
    }) {
        Icon(
            Icons.Filled.Settings,
            contentDescription = "Settings Button",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun Tutorial(){
    val context = LocalContext.current
    IconButton(onClick = {
        Toast.makeText(context,"Clicked on Tutorial",Toast.LENGTH_SHORT).show()
    }) {
        Icon(
            Icons.Filled.Info,
            contentDescription = "Settings Button",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun AppName(){
    Text(
        "App Name",
        modifier = Modifier
            .fillMaxWidth()
            .rotate(-25f),
        textAlign = TextAlign.Center,
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun Credits(){
    val context = LocalContext.current
    TextButton(onClick = {
        Toast.makeText(context,"Clicked on Credits", Toast.LENGTH_SHORT).show()
    },  shape = CircleShape,
        contentPadding = PaddingValues(16.dp),

        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent,
            contentColor = Color.White)
    ){
        Text("Credits",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
        )
    }
}
