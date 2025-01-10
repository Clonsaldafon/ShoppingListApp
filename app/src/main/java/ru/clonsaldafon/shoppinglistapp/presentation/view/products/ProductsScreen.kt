package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.Typography
import ru.clonsaldafon.shoppinglistapp.ui.theme.White
import java.text.DateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    viewModel: ProductsViewModel = hiltViewModel(),
    groupId: String?,
    groupName: String?,
    groupDescription: String?,
    code: String?
) {
    var expanded by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()

    viewModel.setGroupId(groupId ?: "0")
    viewModel.loadProducts()

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
                        text = groupName ?: "",
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
                                    navController?.navigate(
                                        Routes.GroupInfo.createRoute(
                                            groupId, groupName, groupDescription, code
                                        )
                                    )
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
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = DarkGray,
                        shape = RoundedCornerShape(15.dp)
                    ),
                onClick = {
                    navController?.navigate(
                        Routes.AddProduct.createRoute(
                            groupId, groupName, groupDescription, code
                        )
                    )
                },
                shape = RoundedCornerShape(15.dp),
                containerColor = Orange,
                contentColor = DarkGray
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null
                    )

                    Text(
                        text = "Добавить",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
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
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        color = Orange
                    )
                } else {
                    if (uiState.products.isNullOrEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Список пуст",
                                style = TextStyle(
                                    color = Black,
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    } else {
                        Column(
                            modifier = modifier
                                .fillMaxSize()
                                .background(
                                    color = White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .padding(
                                    vertical = 20.dp,
                                    horizontal = 40.dp
                                )
                        ) {
                            LazyColumn {
                                val dates = mutableSetOf<String>()
                                val formatter = DateTimeFormatter.ofPattern(
                                    "dd.MM.yyyy"
                                )

                                items(uiState.products ?: listOf()) {
                                    val date = ZonedDateTime.parse(it.createdAt)
                                    val formattedDate = date.format(formatter)

                                    if (!dates.contains(formattedDate)) {
                                        DayList(
                                            date = formattedDate,
                                            products = uiState.products ?: listOf()
                                        )

                                        dates.add(formattedDate)
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true,
    locale = "ru"
)
@Composable
fun MyPreview() {
    ProductsScreen(
        groupId = "1",
        groupName = "my-group",
        groupDescription = "test",
        code = "test"
    )
}