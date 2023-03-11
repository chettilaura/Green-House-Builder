package it.polito.did.gruppo8.screensGio

import android.annotation.SuppressLint
import android.widget.EditText
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun CreateGameScreen() {
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.background_purple),
            contentDescription = "CreateGameScreen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column()
        {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .background(color = Color.Black)
            )
            {
                CGBackButton()
                CreateGameTitle()
            }
            CityName() //da creare textField per inserire nome città
        }
    }
}

@Composable
fun CreateGameTitle()
{
    Text(
        "NEW GAME",
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun CGBackButton() {
    val context = LocalContext.current
    IconButton(
        onClick = {
            Toast.makeText(context, "Clicked on back Button", Toast.LENGTH_SHORT).show()
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
fun CityName() {

    Column() {

        Text(
            "CITY NAME:",
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )
        }
    }




