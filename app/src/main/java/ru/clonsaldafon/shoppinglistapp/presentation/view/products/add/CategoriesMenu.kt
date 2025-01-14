package ru.clonsaldafon.shoppinglistapp.presentation.view.products.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun CategoriesMenu(
    category: String,
    categories: List<Category>,
    onEvent: (AddProductEvent) -> Unit
) {
    Box {
        var expanded by remember { mutableStateOf(false) }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable { expanded = true },
            value = category,
            textStyle = TextStyle(
                color = DarkGray,
                fontWeight = FontWeight.Bold
            ),
            enabled = false,
            onValueChange = {},
            placeholder = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_category),
                        contentDescription = stringResource(R.string.category),
                        tint = DarkGray
                    )

                    Text(
                        text = stringResource(R.string.category),
                        style = TextStyle(
                            color = DarkGray,
                            fontSize = 14.sp
                        )
                    )
                }
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
            colors = TextFieldDefaults.colors(
                disabledContainerColor = White,
                disabledTextColor = DarkGray,
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = White,
                unfocusedIndicatorColor = Color.Transparent,
                errorContainerColor = White,
                errorIndicatorColor = Color.Red
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
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onEvent(AddProductEvent.OnCategoryChanged(category))
                    },
                    text = {
                        Text(
                            text = category.name ?: "",
                            style = TextStyle(
                                color = DarkGray,
                                fontSize = 14.sp
                            )
                        )
                    }
                )
            }
        }
    }
}