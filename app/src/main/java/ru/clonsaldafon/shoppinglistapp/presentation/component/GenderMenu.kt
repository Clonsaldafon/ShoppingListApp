package ru.clonsaldafon.shoppinglistapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.view.signup.SignUpEvent
import ru.clonsaldafon.shoppinglistapp.presentation.view.signup.SignUpUiState
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.Blue
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Pink
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun GenderMenu(
    value: String,
    uiState: SignUpUiState,
    onEvent: (SignUpEvent) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val genders = listOf(
        stringResource(R.string.male),
        stringResource(R.string.female)
    )

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .border(
                    width = 1.dp,
                    color = DarkGray,
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable { expanded = !expanded },
            shape = RoundedCornerShape(15.dp),
            value = if (value != "") value else genders[0],
            textStyle = TextStyle(
                color = Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            enabled = false,
            onValueChange = {
                onEvent(SignUpEvent.OnGenderChanged(it))
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .background(
                            color = DarkGray,
                            shape = RoundedCornerShape(
                                topStart = 15.dp,
                                bottomStart = 15.dp
                            )
                        )
                        .padding(15.dp),
                    imageVector =
                    if (uiState.gender == ""
                        || uiState.gender == stringResource(R.string.male)
                    )
                        ImageVector.vectorResource(R.drawable.ic_male)
                    else
                        ImageVector.vectorResource(R.drawable.ic_female),
                    tint = White,
                    contentDescription = stringResource(R.string.gender)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector =
                    if (expanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = DarkGray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            )
        )

        DropdownMenu(
            modifier = Modifier
                .background(
                    color = White
                ),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            genders.forEach { gender ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onEvent(SignUpEvent.OnGenderChanged(gender))
                    },
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            val imageVector =
                                if (gender == stringResource(R.string.male))
                                    ImageVector.vectorResource(R.drawable.ic_male)
                                else
                                    ImageVector.vectorResource(R.drawable.ic_female)
                            val tint =
                                if (gender == stringResource(R.string.male))
                                    Blue
                                else
                                    Pink

                            Icon(
                                imageVector = imageVector,
                                contentDescription = stringResource(
                                    R.string.gender
                                ),
                                tint = tint
                            )

                            Text(
                                text = gender,
                                style = TextStyle(
                                    color = DarkGray,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 5.dp,
                    end = 5.dp
                ),
            text = "",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Right
            )
        )
    }
}