package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatRegular
import it.polito.did.gruppo8.screens.caveatSemiBold

/***
 * Generalized Box Input Form to register text inputs from the user.
 *
 * @param title text showed on the component.
 * @param label short text over the input field.
 * @param targetValue the string in which you want to save the user input.
 * @param updateTargetCallback action to trigger when field value change. ex. function: {targetValue = it}.
 * @param fraction used to set the height of the box
 */
@Composable
fun MyFormBox (title: String, label: String, targetValue: String, updateTargetCallback : (String)->Unit, fraction:Float) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(fraction)
        .padding(2.dp),
        backgroundColor = colorResource(id = R.color.emerald),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontFamily = caveatSemiBold,
                color = Color.White,
                style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.size(4.dp))

            //var fieldValue by remember { mutableStateOf(TextFieldValue(""))}
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    /*.background(colorResource(id = emerald))*/
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.cal_poly_green),
                        shape = RoundedCornerShape(10.dp)
                    ),
                value = targetValue,
                //onValueChange = { fieldValue = it },
                onValueChange = updateTargetCallback,
                label = {
                    Text(text = label,
                        color = Color.White,
                        fontFamily = caveatRegular,
                        style = MaterialTheme.typography.body1
                    )
                },
                /*colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = asparagus)
                )*/
            )
        }
    }
}
