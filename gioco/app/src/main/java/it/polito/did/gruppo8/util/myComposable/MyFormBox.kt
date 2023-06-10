package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyFormBox(
    title: String,
    label: String,
    targetValue: String,
    updateTargetCallback: (String) -> Unit,
    fraction: Float
) {
    Card(
        modifier = Modifier
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
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontFamily = caveatSemiBold,
                color = Color.White,
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.size(4.dp))

            val keyboardController = LocalSoftwareKeyboardController.current
            val keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)

            val customTextSelectionColors = TextSelectionColors(
                handleColor = Color.Transparent,
                backgroundColor = Color(0xD724472C)
            )

            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {

                TextField(

                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        disabledTextColor = Color(0xFF43C366),
                        cursorColor = Color.Black,
                        errorCursorColor = Color(0xFF43C366),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Red,
                        disabledIndicatorColor = Color(0xFF43C366),
                        leadingIconColor = Color(0xFF43C366),
                        disabledLeadingIconColor = Color(0xFF43C366),
                        errorLeadingIconColor = Color(0xFF43C366),
                        trailingIconColor = Color(0xFF43C366),
                        disabledTrailingIconColor = Color(0xFF43C366),
                        errorTrailingIconColor = Color(0xFF43C366),
                        focusedLabelColor = Color(0xFF43C366),
                        unfocusedLabelColor = Color(0xFF43C366),
                        disabledLabelColor = Color(0xFF43C366),
                        errorLabelColor = Color(0xFF43C366),
                        placeholderColor = Color.Red,
                        disabledPlaceholderColor = Color.Red
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .border(
                            shape = RoundedCornerShape(10.dp),
                            width = 2.dp,
                            color = Color(0xFF294D31)
                        ),

                    value = targetValue,
                    onValueChange = { newValue ->
                        if (newValue.length <= 13) {    //lunghezza massima dei caratteri inseriti
                            updateTargetCallback(newValue)
                        }
                    },
                    label = {
                        Text(
                            text = label,
                            color = Color.White,
                            fontFamily = caveatRegular,
                            style = MaterialTheme.typography.body1
                        )
                    },
                    keyboardOptions = keyboardOptions,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hideSoftwareKeyboard()
                        }
                    )
                )
            }
        }
    }
}


@Preview (showSystemUi = true, showBackground = true)
@Composable
fun MyFormBoxPreview() {

}