package it.polito.did.gruppo8.util.myComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.screens.caveatRegular
import it.polito.did.gruppo8.screens.caveatSemiBold

/***
 * Generalized on-line Input Form to register text inputs from the user.
 *
 * @param title text showed on the component.
 * @param label short text over the input field.
 * @param targetValue the string in which you want to save the user input.
 * @param updateTargetCallback action to trigger when field value change. ex. function: {targetValue = it}.
 */
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyFormLine(
    title: String,
    label: String,
    targetValue: String,
    updateTargetCallback : (String)->Unit
) {
    Card(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.emerald),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontFamily = caveatSemiBold,
                color = Color.White,
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.size(4.dp))

            val keyboardController = LocalSoftwareKeyboardController.current
            val keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )

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
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.cal_poly_green),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth(0.9f),
                    value = targetValue,
                    onValueChange = { newValue ->
                        if (newValue.length <= 13) {
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
                        onDone = { keyboardController?.hideSoftwareKeyboard() }
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun MyFormLinePreview() {

}