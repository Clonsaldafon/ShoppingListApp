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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkOrange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Green
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun AuthOutlinedTextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLength: Int = 16
) {
    var isPasswordVisibilityOn by remember { mutableStateOf(false) }

    Column{
        Text(
            modifier = Modifier
                .padding(bottom = 15.dp),
            text = label,
            style = TextStyle(
                color = DarkGreen,
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    width = 2.dp,
                    color = Green,
                    shape = RoundedCornerShape(12.dp)
                ),
            value = value,
            onValueChange = { if (it.length <= maxLength) onValueChange(it) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                cursorColor = DarkOrange
            ),
            textStyle = TextStyle(
                color = DarkGreen,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            leadingIcon = {
                Column(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .background(
                            color = Green,
                            shape = RoundedCornerShape(
                                topStart = 12.dp,
                                bottomStart = 12.dp
                            )
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp),
                        imageVector = leadingIcon,
                        tint = White,
                        contentDescription = null
                    )
                }
            },
            visualTransformation =
                if (isPasswordVisibilityOn)
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
                            isPasswordVisibilityOn = !isPasswordVisibilityOn
                        }
                    ) {
                        Icon(
                            imageVector =
                                if (isPasswordVisibilityOn)
                                    ImageVector.vectorResource(R.drawable.ic_visibility_on)
                                else
                                    ImageVector.vectorResource(R.drawable.ic_visibility_off),
                            contentDescription = null,
                            tint = Green
                        )
                    }
                }
            }
        )

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