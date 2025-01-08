package ru.clonsaldafon.shoppinglistapp.presentation.view.groups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
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
import ru.clonsaldafon.shoppinglistapp.ui.theme.Typography
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(
                    top = 20.dp,
                    bottom = 20.dp
                ),
                colors = topAppBarColors(
                    containerColor = DarkGray,
                    titleContentColor = White
                ),
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(R.string.groups),
                        style = Typography.titleLarge
                    )
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
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_groups),
                            contentDescription = stringResource(R.string.groups),
                            tint = Orange
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp),
                        onClick = { navController?.navigate(Routes.Profile.route) }
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_profile),
                            contentDescription = stringResource(R.string.my_profile),
                            tint = White
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            var expanded by remember { mutableStateOf(false) }

            Box {
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
                    onClick = { expanded = true }
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
                                text = "Создать",
                                style = TextStyle(fontSize = 16.sp)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        },
                        onClick = { expanded = false },
                        colors = MenuDefaults.itemColors(
                            textColor = Black,
                            trailingIconColor = Black
                        )
                    )

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Войти",
                                style = TextStyle(fontSize = 16.sp)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null
                            )
                        },
                        onClick = { expanded = false },
                        colors = MenuDefaults.itemColors(
                            textColor = Black,
                            trailingIconColor = Black
                        )
                    )
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
                    )
            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "Вы еще не\nсостоите в группе",
//                        style = TextStyle(
//                            color = Black,
//                            fontSize = 24.sp,
//                            textAlign = TextAlign.Center
//                        )
//                    )
//                }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    vertical = 20.dp,
                    horizontal = 40.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val groups = listOf(
                    "test", "test", "test", "test", "test", "test", "test", "test", "test"
                )
                items(groups) { group ->
                    GroupItem(
                        navController = navController,
                        title = group
                    )
                }
            }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    locale = "ru"
)
@Composable
fun MyPreview() {
    GroupsScreen()
}