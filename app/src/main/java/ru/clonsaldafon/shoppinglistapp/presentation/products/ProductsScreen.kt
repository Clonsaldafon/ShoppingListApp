package ru.clonsaldafon.shoppinglistapp.presentation.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.clonsaldafon.shoppinglistapp.presentation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkOrange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Green
import ru.clonsaldafon.shoppinglistapp.ui.theme.LightGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.White
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null
) {
    Scaffold(
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
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
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
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null,
                            tint = White
                        )
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
                        color = DarkOrange,
                        shape = RoundedCornerShape(60.dp)
                    ),
                onClick = {
                    navController?.navigate(Routes.AddProduct.route)
                }
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
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = White
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = 20.dp,
                        horizontal = 40.dp
                    ),
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
            }
        }
    }
}

@Composable
fun DayList(
    date: String
) {
    var expanded by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null,
            tint = DarkGreen
        )

        Text(
            text = date,
            style = TextStyle(
                color = DarkGreen,
                fontSize = 14.sp
            )
        )

        IconButton(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            onClick = {
                expanded = !expanded
            }
        ) {
            Icon(
                imageVector =
                    if (expanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = DarkGreen
            )
        }
    }

    if (expanded) {
        Column(
            modifier = Modifier
                .padding(
                    top = 20.dp,
                    bottom = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProductItem(
                title = "Молоко питьевое",
                count = 2,
                infoVisibility = false
            )

            ProductItem(
                title = "Курица",
                count = 1
            )

            ProductItem(
                title = "Мука",
                count = 1,
                bought = true,
                price = 150.0,
                infoVisibility = false
            )
        }
    }
}

@Composable
fun ProductItem(
    title: String,
    count: Int,
    bought: Boolean = false,
    price: Double = 0.0,
    infoVisibility: Boolean = false
) {
    val isInfoHidden = remember { mutableStateOf(!infoVisibility) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = if (bought) White else LightGreen,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = DarkGreen,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    IconButton(
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp),
                        onClick = {
                            isInfoHidden.value = !isInfoHidden.value
                        }
                    ) {
                        Icon(
                            imageVector =
                                if (isInfoHidden.value)
                                    Icons.Filled.KeyboardArrowDown
                                else
                                    Icons.Filled.KeyboardArrowUp,
                            contentDescription = null,
                            tint = Green
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "$count шт.",
                        style = TextStyle(
                            color = Green,
                            fontSize = 11.sp
                        )
                    )

                    IconButton(
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp),
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = Orange
                        )
                    }
                }
            }

            Checkbox(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                checked = bought,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(
                    checkedColor = Green
                )
            )
        }

        if (!isInfoHidden.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Добавлено:",
                            style = TextStyle(
                                color = Green,
                                fontSize = 11.sp
                            )
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Антон",
                                style = TextStyle(
                                    color = DarkGreen,
                                    fontSize = 11.sp
                                )
                            )

                            Image(
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp),
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )
                        }
                    }

                    if (bought) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Куплено:",
                                style = TextStyle(
                                    color = Green,
                                    fontSize = 11.sp
                                )
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Антон",
                                    style = TextStyle(
                                        color = DarkGreen,
                                        fontSize = 11.sp
                                    )
                                )

                                Image(
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp),
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }

                if (bought) {
                    Text(
                        text = "$price ₽",
                        style = TextStyle(
                            color = Orange,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyPreview() {
    ProductsScreen()
}