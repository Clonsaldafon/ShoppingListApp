package ru.clonsaldafon.shoppinglistapp.presentation.view.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.Green
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.White
import kotlin.math.exp

@Composable
fun GroupItem(
    title: String,
    members: Int
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 17.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.LightGray
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = DarkGreen,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "$members чел.",
                    style = TextStyle(
                        color = Green,
                        fontSize = 20.sp
                    )
                )
            }
        }

        Box {
            IconButton(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Orange
                )
            }

            DropdownMenu(
                modifier = Modifier
                    .background(
                        color = White
                    ),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Покинуть группу",
                            style = TextStyle(
                                fontSize = 16.sp
                            )
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ExitToApp,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        expanded = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = Red,
                        trailingIconColor = Red
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun GroupItemPreview() {
    GroupItem(
        title = "test",
        members = 1
    )
}