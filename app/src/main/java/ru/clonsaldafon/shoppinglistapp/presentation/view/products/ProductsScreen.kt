package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.Typography
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var isAddProductWindowHidden by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(
                    left = 20.dp,
                    top = 20.dp,
                    right = 20.dp,
                    bottom = 20.dp
                ),
                colors = topAppBarColors(
                    containerColor = DarkGray,
                    titleContentColor = White
                ),
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp),
                        onClick = { navController?.navigate(Routes.Groups.route) }
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = White
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "my-group",
                        style = Typography.titleLarge
                    )
                },
                actions = {
                    Box {
                        IconButton(
                            modifier = Modifier
                                .width(35.dp)
                                .height(35.dp),
                            onClick = { expanded = true }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .fillMaxSize(),
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null,
                                tint = White
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
                                        text = "Инфо о группе",
                                        style = TextStyle(fontSize = 16.sp)
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    expanded = false
                                    navController?.navigate(Routes.GroupInfo.route)
                                },
                                colors = MenuDefaults.itemColors(
                                    textColor = Black,
                                    trailingIconColor = Black
                                )
                            )

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
            )
        },
        floatingActionButton = {
            IconButton(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(60.dp)
                    )
                    .background(
                        color = Orange,
                        shape = RoundedCornerShape(60.dp)
                    ),
                onClick = { isAddProductWindowHidden = false }
            ) {
                Icon(
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = White
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = DarkGray
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        color = White,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            vertical = 20.dp,
                            horizontal = 40.dp
                        )
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        LazyColumn {
                            item {
                                DayList(
                                    date = "01.01.2025"
                                )
                            }

                            item {
                                DayList(
                                    date = "31.12.2024"
                                )
                            }
                        }

                        if (!isAddProductWindowHidden) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(
                                        elevation = 16.dp,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        color = Black,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    IconButton(
                                        modifier = Modifier
                                            .width(25.dp)
                                            .height(25.dp),
                                        onClick = { isAddProductWindowHidden = true }
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null,
                                            tint = Red
                                        )
                                    }
                                }

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(20.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Добавить продукт в список",
                                        style = TextStyle(
                                            color = White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    )

                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        CategoriesMenu()

                                        ProductsMenu()

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
                                            onValueChange = {},
                                            label = {
                                                Text(
                                                    text = stringResource(R.string.quantity),
                                                    style = TextStyle(
                                                        color = White,
                                                        fontSize = 14.sp
                                                    )
                                                )
                                            },
                                            colors = TextFieldDefaults.colors(
                                                disabledContainerColor = DarkGray,
                                                disabledTextColor = White,
                                                disabledIndicatorColor = Color.Transparent,
                                                unfocusedContainerColor = DarkGray,
                                                unfocusedIndicatorColor = Color.Transparent,
                                                focusedContainerColor = DarkGray,
                                                focusedIndicatorColor = Color.Transparent,
                                                errorContainerColor = DarkGray,
                                                errorIndicatorColor = Color.Red,
                                                cursorColor = Orange
                                            ),
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number
                                            )
                                        )
                                    }

                                    Button(
                                        modifier = Modifier
                                            .shadow(
                                                elevation = 5.dp,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .background(
                                                color = Orange,
                                                shape = RoundedCornerShape(12.dp)
                                            ),
                                        onClick = { isAddProductWindowHidden = true },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Orange,
                                            contentColor = Black
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Text(
                                            text = stringResource(R.string.add),
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    locale = "ru"
)
@Composable
fun MyPreview() {
    ProductsScreen()
}