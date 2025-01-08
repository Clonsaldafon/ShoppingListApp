package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.LightOrange
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun CategoriesMenu() {
    Box {
        val categories = listOf(
            "молочные продукты",
            "мясные продукты",
            "рыбные продукты"
        )

        var expanded by remember { mutableStateOf(false) }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = DarkGray,
                    shape = RoundedCornerShape(16.dp)
                ),
            value = "",
            textStyle = TextStyle(
                color = White,
                fontWeight = FontWeight.Bold
            ),
            enabled = false,
            onValueChange = {},
            label = {
                Text(
                    text = stringResource(R.string.category),
                    style = TextStyle(
                        color = White,
                        fontSize = 14.sp
                    )
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector =
                        if (expanded)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = White
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = DarkGray,
                disabledTextColor = White,
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = DarkGray,
                unfocusedIndicatorColor = Color.Transparent,
                errorContainerColor = DarkGray,
                errorIndicatorColor = Color.Red
            )
        )

        DropdownMenu(
            modifier = Modifier
                .background(color = DarkGray),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = { expanded = false },
                    text = {
                        Text(
                            text = category,
                            style = TextStyle(
                                color = White,
                                fontSize = 14.sp
                            )
                        )
                    }
                )
            }
        }
    }
}