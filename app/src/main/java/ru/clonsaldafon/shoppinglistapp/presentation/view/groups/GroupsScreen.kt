package ru.clonsaldafon.shoppinglistapp.presentation.view.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.component.LoadingProgressBar
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Typography
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    viewModel: GroupsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

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
                            shape = CircleShape
                        )
                        .background(
                            color = Orange,
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = DarkGray,
                            shape = CircleShape
                        ),
                    onClick = { expanded = true }
                ) {
                    Icon(
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp),
                        imageVector = Icons.Rounded.Add,
                        contentDescription = stringResource(R.string.add),
                        tint = DarkGray
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
                                contentDescription = stringResource(R.string.add)
                            )
                        },
                        onClick = {
                            expanded = false
                            navController?.navigate(Routes.CreateGroup.route)
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Black,
                            trailingIconColor = Black
                        )
                    )

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(R.string.authorize),
                                style = TextStyle(fontSize = 16.sp)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            expanded = false
                            navController?.navigate(Routes.JoinToGroup.route)
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Black,
                            trailingIconColor = Black
                        )
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        if (uiState.error.isNotEmpty()) {
            showSnackbar = true
        }

        LaunchedEffect(showSnackbar) {
            if (showSnackbar) {
                snackbarHostState.showSnackbar(uiState.error)
                showSnackbar = false
            }
        }

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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isLoading) {
                    LoadingProgressBar(
                        color = Orange
                    )
                } else {
                    if (uiState.groups.isNullOrEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Вы еще не\nсостоите в группе",
                                style = TextStyle(
                                    color = Black,
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentPadding = PaddingValues(
                                vertical = 20.dp,
                                horizontal = 40.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(uiState.groups ?: listOf()) { group ->
                                GroupItem(
                                    navController = navController,
                                    groupId = group.id.toString(),
                                    groupName = group.name,
                                    groupDescription = group.description,
                                    code = group.code
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
    showBackground = true,
    locale = "ru"
)
@Composable
fun MyPreview() {
    GroupsScreen()
}