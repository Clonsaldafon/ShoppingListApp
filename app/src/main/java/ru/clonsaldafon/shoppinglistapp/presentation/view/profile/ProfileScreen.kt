package ru.clonsaldafon.shoppinglistapp.presentation.view.profile

import android.graphics.ColorSpace.Rgb
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.LightOrange
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
                        text = stringResource(R.string.my_profile),
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
                                .background(color = White),
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = stringResource(R.string.edit),
                                        style = TextStyle(fontSize = 16.sp)
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
                                    textColor = Black,
                                    trailingIconColor = Black
                                )
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Выйти",
                                        style = TextStyle(fontSize = 16.sp)
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
                windowInsets = WindowInsets(
                    top = 0.dp,
                    bottom = 15.dp
                ),
                containerColor = DarkGray
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp),
                        onClick = { navController?.navigate(Routes.Groups.route) }
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_groups),
                            contentDescription = stringResource(R.string.groups),
                            tint = White
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp),
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_profile),
                            contentDescription = stringResource(R.string.my_profile),
                            tint = Orange
                        )
                    }
                }
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
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = White,
                        shape = RoundedCornerShape(15.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color =
                            if (isEditWindowHidden) Color.Transparent
                            else Black.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
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
                                    .clip(shape = CircleShape)
                                    .background(color = DarkGray)
                                    .padding(10.dp),
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Orange
                            )

                            Text(
                                text = "my-login",
                                style = TextStyle(
                                    color = Black,
                                    fontSize = 24.sp
                                )
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_male),
                                    contentDescription = null,
                                    tint = DarkGray
                                )

                                Text(
                                    text = "Мужчина",
                                    style = TextStyle(
                                        color = DarkGray,
                                        fontSize = 16.sp,
                                    )
                                )
                            }
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
                                        text = stringResource(R.string.data_editing),
                                        style = TextStyle(
                                            color = DarkGray,
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
                                                text = stringResource(R.string.new_login),
                                                style = TextStyle(
                                                    color = DarkGray,
                                                    fontSize = 14.sp
                                                )
                                            )
                                        },
                                        colors = TextFieldDefaults.colors(
                                            disabledContainerColor = White,
                                            disabledTextColor = Black,
                                            disabledIndicatorColor = Color.Transparent,
                                            unfocusedContainerColor = White,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            focusedContainerColor = LightOrange,
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
                                            contentColor = Black
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Text(
                                            text = stringResource(R.string.save),
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
    showSystemUi = true,
    showBackground = true, locale = "ru"
)
@Composable
fun MyPreview() {
    ProfileScreen()
}