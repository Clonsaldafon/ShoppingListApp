package ru.clonsaldafon.shoppinglistapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.view.signup.SignUpEvent
import ru.clonsaldafon.shoppinglistapp.presentation.view.signup.SignUpUiState
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun AuthOutlinedTextField(
    value: String,
    uiState: SignUpUiState,
    onEvent: (SignUpEvent) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLength: Int = 30
) {
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .border(
                    width = 2.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(15.dp)
                ),
            shape = RoundedCornerShape(15.dp),
            value = value,
            onValueChange = {
                if (it.length <= maxLength)
                    if (visualTransformation == VisualTransformation.None)
                        onEvent(SignUpEvent.OnLoginChanged(it))
                    else
                        onEvent(SignUpEvent.OnPasswordChanged(it))
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = label,
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = DarkGray,
                focusedBorderColor = DarkGray,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                cursorColor = Orange,
                errorBorderColor = Red
            ),
            textStyle = TextStyle(
                color = Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            leadingIcon = {
                Column(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .background(
                            color = DarkGray,
                            shape = RoundedCornerShape(
                                topStart = 15.dp,
                                bottomStart = 15.dp
                            )
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        imageVector = leadingIcon,
                        tint = White,
                        contentDescription = null
                    )
                }
            },
            visualTransformation =
                if (!uiState.isPasswordHidden)
                    VisualTransformation.None
                else
                    visualTransformation,
            keyboardOptions = keyboardOptions,
            trailingIcon = {
                if (visualTransformation != VisualTransformation.None) {
                    IconButton(
                        modifier = Modifier
                            .width(50.dp)
                            .width(50.dp),
                        onClick = {
                            onEvent(
                                SignUpEvent.OnPasswordVisibilityChanged(!uiState.isPasswordHidden)
                            )
                        }
                    ) {
                        Icon(
                            imageVector =
                                if (!uiState.isPasswordHidden)
                                    ImageVector.vectorResource(R.drawable.ic_visibility_on)
                                else
                                    ImageVector.vectorResource(R.drawable.ic_visibility_off),
                            contentDescription = null,
                            tint = DarkGray
                        )
                    }
                }
            },
            isError =
                if (visualTransformation == VisualTransformation.None)
                    uiState.isLoginInvalid
                else
                    uiState.isPasswordInvalid,
            singleLine = true
        )

        if (uiState.loginErrorMessage.isNotEmpty() &&
            visualTransformation == VisualTransformation.None) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 5.dp,
                        end = 5.dp
                    ),
                text = uiState.loginErrorMessage,
                style = TextStyle(
                    color = Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 5.dp,
                        end = 5.dp
                    ),
                text =
                if(value.length >= maxLength - 5) "${value.length} / $maxLength"
                else "",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Right
                )
            )
        }
    }
}