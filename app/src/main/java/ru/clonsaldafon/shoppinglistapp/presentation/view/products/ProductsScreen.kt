package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import android.icu.text.SimpleDateFormat
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
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import ru.clonsaldafon.shoppinglistapp.data.model.Product
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.Routes
import ru.clonsaldafon.shoppinglistapp.presentation.view.groups.create.CreateGroupEvent
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.LightOrange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.Typography
import ru.clonsaldafon.shoppinglistapp.ui.theme.White
import java.text.DateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

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
                            contentDescription = stringResource(R.string.back),
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
                                contentDescription = stringResource(R.string.menu),
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
                                        contentDescription = stringResource(R.string.info)
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
                                        contentDescription = stringResource(R.string.exit)
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
                        contentDescription = stringResource(R.string.add)
                    )

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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                vertical = 20.dp,
                                horizontal = 40.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (uiState.products.isNullOrEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.empty_list),
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
                            ) {
                                LazyColumn {
                                    val formatter = SimpleDateFormat(
                                        "dd.MM.yyyy",
                                        Locale.getDefault()
                                    )
                                    val inputFormat = SimpleDateFormat(
                                        "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
                                        Locale.getDefault()
                                    )

                                    val lists = mutableMapOf<String, MutableList<Product>>()
                                    uiState.products?.forEach {
                                        val date = inputFormat.parse(it.createdAt)
                                        val formattedDate = formatter.format(date)

                                        if (!lists.contains(formattedDate))
                                            lists[formattedDate] = mutableListOf(it)
                                        else
                                            lists[formattedDate]?.add(it)
                                    }

                                    items(lists.toList().reversed()) { pair ->
                                        DayList(
                                            groupId = uiState.groupId,
                                            date = pair.first,
                                            products = pair.second.sortedBy { it.boughtBy },
                                            uiState = uiState,
                                            onEvent = viewModel::onEvent
                                        )
                                    }
                                }
                            }
                        }

                        if (!uiState.isBuyWindowHidden) {
                            Column(
                                modifier = Modifier
                                    .shadow(
                                        elevation = 10.dp,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .background(
                                        color = White,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    IconButton(
                                        modifier = Modifier
                                            .width(20.dp)
                                            .height(20.dp),
                                        onClick = { viewModel.resetCurrentValues() }
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            imageVector = Icons.Rounded.Close,
                                            contentDescription = stringResource(R.string.close),
                                            tint = Red
                                        )
                                    }
                                }

                                Text(
                                    text = stringResource(R.string.how_much_spend),
                                    style = TextStyle(
                                        color = DarkGray,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                )

                                Column {
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
                                        value =
                                        if (uiState.currentPrice == null) ""
                                        else uiState.currentPrice.toString(),
                                        onValueChange = {
                                            viewModel.onEvent(
                                                ProductsEvent.OnCurrentPriceUpdated(it)
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = stringResource(R.string.price),
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
                                        ),
                                        singleLine = true,
                                        isError = uiState.isCurrentPriceInvalid
                                    )
                                }

                                Button(
                                    modifier = Modifier
                                        .shadow(
                                            elevation = 5.dp,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .background(
                                            color = if (uiState.isValid) Orange else White,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = DarkGray,
                                            shape = RoundedCornerShape(15.dp)
                                        ),
                                    onClick = {
                                        viewModel.onEvent(
                                            ProductsEvent.OnProductUpdated(
                                                groupId!!, uiState.currentProductId
                                            )
                                        )
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Orange,
                                        contentColor = DarkGray,
                                        disabledContainerColor = White,
                                        disabledContentColor = DarkGray
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    enabled = uiState.isValid
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