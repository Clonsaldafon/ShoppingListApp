package ru.clonsaldafon.shoppinglistapp.presentation.view.profile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.Green
import ru.clonsaldafon.shoppinglistapp.ui.theme.LightGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.Typography
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var isEditWindowHidden by remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    )
                    .background(
                        color = Green,
                        shape = RoundedCornerShape(
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    ),
                windowInsets = WindowInsets(
                    left = 20.dp,
                    top = 20.dp,
                    right = 20.dp,
                    bottom = 20.dp
                ),
                colors = topAppBarColors(
                    containerColor = Green,
                    titleContentColor = White
                ),
                navigationIcon = {
                    Column(
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp)
                    ) {}
                },
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Мой профиль",
                        style = Typography.titleLarge
                    )
                },
                actions = {
                    Box {
                        IconButton(
                            modifier = Modifier
                                .width(35.dp)
                                .height(35.dp),
                            onClick = { expanded = !expanded }
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
                                .background(
                                    color = White
                                ),
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Редактировать",
                                        style = TextStyle(
                                            fontSize = 16.sp
                                        )
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    isEditWindowHidden = false
                                    expanded = false
                                },
                                colors = MenuDefaults.itemColors(
                                    textColor = DarkGreen,
                                    trailingIconColor = DarkGreen
                                )
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Выйти",
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
                                onClick = {},
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
        bottomBar = {
            BottomAppBar(
                modifier = modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    ),
                containerColor = Green
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp),
                        onClick = {
                            navController?.navigate(Routes.Groups.route)
                        }
                    ) {
                        Image(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            bitmap = ImageBitmap.imageResource(R.drawable.groups),
                            contentDescription = "Группы"
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp),
                        onClick = {}
                    ) {
                        Image(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            bitmap = ImageBitmap.imageResource(R.drawable.profile_selected),
                            contentDescription = "Профиль"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = White
                )
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(
                                shape = CircleShape
                            )
                            .background(
                                color = Green
                            )
                            .padding(10.dp),
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Orange
                    )

                    Text(
                        text = "my-login",
                        style = TextStyle(
                            color = DarkGreen,
                            fontSize = 24.sp
                        )
                    )
                }

                if (!isEditWindowHidden) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 16.dp,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(
                                color = White,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(20.dp)
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
                                onClick = { isEditWindowHidden = true }
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
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Text(
                                text = "Изменение данных",
                                style = TextStyle(
                                    color = Green,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

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
                                    ),
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Новый логин",
                                        style = TextStyle(
                                            color = Green,
                                            fontSize = 14.sp
                                        )
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    disabledContainerColor = White,
                                    disabledTextColor = DarkGreen,
                                    disabledIndicatorColor = Color.Transparent,
                                    unfocusedContainerColor = White,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedContainerColor = LightGreen,
                                    focusedIndicatorColor = Color.Transparent,
                                    errorContainerColor = White,
                                    errorIndicatorColor = Color.Red,
                                    cursorColor = Orange
                                )
                            )

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
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Orange,
                                    contentColor = DarkGreen
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "Сохранить",
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

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MyPreview() {
    ProfileScreen()
}