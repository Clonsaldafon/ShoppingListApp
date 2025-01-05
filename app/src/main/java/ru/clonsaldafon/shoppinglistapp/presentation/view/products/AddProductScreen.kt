package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.Green
import ru.clonsaldafon.shoppinglistapp.ui.theme.LightGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = White),
                windowInsets = WindowInsets(
                    left = 20.dp,
                    top = 20.dp,
                    right = 20.dp,
                    bottom = 20.dp
                ),
                colors = topAppBarColors(
                    containerColor = White,
                    titleContentColor = Green
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController?.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Green
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(R.string.product_adding),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = White),
                windowInsets = WindowInsets(
                    left = 20.dp,
                    top = 20.dp,
                    right = 20.dp,
                    bottom = 20.dp
                ),
                containerColor = White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
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
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = White)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = 20.dp,
                        horizontal = 40.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(20.dp)
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
                            color = White,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(
                            text = stringResource(R.string.quantity),
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
                        focusedContainerColor = White,
                        focusedIndicatorColor = Color.Transparent,
                        errorContainerColor = White,
                        errorIndicatorColor = Color.Red,
                        cursorColor = Orange
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }
}

@Composable
fun CategoriesMenu() {
    Box {
        val categories = listOf(
            "молочные продукты",
            "мясные продукты",
            "рыбные продукты"
        )

        var expanded by remember { mutableStateOf(false) }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = LightGreen,
                    shape = RoundedCornerShape(16.dp)
                ),
            value = "",
            textStyle = TextStyle(
                color = DarkGreen,
                fontWeight = FontWeight.Bold
            ),
            enabled = false,
            onValueChange = {},
            label = {
                Text(
                    text = stringResource(R.string.category),
                    style = TextStyle(
                        color = Green,
                        fontSize = 14.sp
                    )
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector =
                        if (expanded)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Green
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = White,
                disabledTextColor = DarkGreen,
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = White,
                unfocusedIndicatorColor = Color.Transparent,
                errorContainerColor = White,
                errorIndicatorColor = Color.Red
            )
        )

        DropdownMenu(
            modifier = Modifier
                .background(color = White),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = { expanded = false },
                    text = {
                        Text(
                            text = category,
                            style = TextStyle(
                                color = Green,
                                fontSize = 14.sp
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun ProductsMenu() {
    Box {
        val products = listOf(
            "молоко",
            "хлеб",
            "мука"
        )
        var expanded by remember { mutableStateOf(false) }

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
            textStyle = TextStyle(
                color = DarkGreen,
                fontWeight = FontWeight.Bold
            ),
            enabled = false,
            onValueChange = {},
            label = {
                Text(
                    text = stringResource(R.string.product),
                    style = TextStyle(
                        color = Green,
                        fontSize = 14.sp
                    )
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector =
                        if (expanded)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Green
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = White,
                disabledTextColor = DarkGreen,
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = White,
                focusedIndicatorColor = Color.Transparent,
                errorContainerColor = White,
                errorIndicatorColor = Color.Red
            )
        )

        DropdownMenu(
            modifier = Modifier
                .background(color = White),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            products.forEach { product ->
                DropdownMenuItem(
                    onClick = { expanded = false },
                    text = {
                        Text(
                            text = product,
                            style = TextStyle(
                                color = Green,
                                fontSize = 14.sp
                            )
                        )
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AddProductPreview() {
    AddProductScreen()
}