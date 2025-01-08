package ru.clonsaldafon.shoppinglistapp.presentation.view.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.sharp.ShoppingCart
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun GroupItem(
    navController: NavController? = null,
    title: String
) {
    var expanded by remember { mutableStateOf(false) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = White)
//            .padding(
//                horizontal = 20.dp,
//                vertical = 17.dp
//            )
            .clickable { navController?.navigate(Routes.Products.route) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    top = 17.dp,
                    bottom = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                imageVector = Icons.Sharp.ShoppingCart,
                contentDescription = null,
                tint = Color.LightGray
            )

            Text(
                text = title,
                style = TextStyle(
                    color = Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Box(
            modifier = Modifier
                .padding(
                    end = 20.dp
                )
        ) {
            IconButton(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                onClick = { expanded = true }
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Orange
                )
            }

            DropdownMenu(
                modifier = Modifier
                    .background(color = White),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(R.string.leave_group),
                            style = TextStyle(fontSize = 16.sp)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ExitToApp,
                            contentDescription = null
                        )
                    },
                    onClick = { expanded = false },
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
        title = "test"
    )
}