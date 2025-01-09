package ru.clonsaldafon.shoppinglistapp.presentation.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.component.AuthOutlinedTextField
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    viewModel: LogInViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(
                    top = 40.dp,
                    bottom = 40.dp
                ),
                colors = topAppBarColors(
                    containerColor = DarkGray,
                    titleContentColor = White
                ),
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Вход в аккаунт",
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            )
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
                    .padding(
                        horizontal = 40.dp
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    AuthOutlinedTextField(
//                        value = "",
//                        onEvent = {},
//                        label = stringResource(R.string.login),
//                        leadingIcon = Icons.Default.Person
//                    )
//
//                    AuthOutlinedTextField(
//                        value = "",
//                        onEvent = {},
//                        label = stringResource(R.string.password),
//                        leadingIcon = Icons.Default.Lock,
//                        visualTransformation = PasswordVisualTransformation(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .border(
                                width = 2.dp,
                                color = Orange,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .shadow(
                                elevation = 15.dp,
//                            if(!login.isNullOrEmpty() && !password.isNullOrEmpty()) 4.dp
//                            else 0.dp,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        shape = RoundedCornerShape(12.dp),
                        onClick = { },
                        enabled = true,
                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = White,
                            disabledContentColor = Black,
                            containerColor = Orange,
                            contentColor = White
                        )
                    ) {
                        Row {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                Text(
                                    text = "Войти".uppercase(),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                )

                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    tint = White
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Еще нет аккаунта?",
                            style = TextStyle(
                                color = Black,
                                fontSize = 14.sp
                            )
                        )

                        Text(
                            modifier = Modifier
                                .clickable {  },
                            text = "Зарегистрироваться",
                            style = TextStyle(
                                color = Orange,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }

//    val uiState by viewModel.uiState.observeAsState()
//    val login by viewModel.login.observeAsState()
//    val password by viewModel.password.observeAsState()
//
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .background(color = Green)
//    ) {
//        AuthTitle(text = stringResource(R.string.authorization))
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .shadow(
//                    elevation = 16.dp,
//                    shape = RoundedCornerShape(
//                        topStart = 30.dp,
//                        topEnd = 30.dp
//                    )
//                )
//                .background(
//                    color = White,
//                    shape = RoundedCornerShape(
//                        topStart = 30.dp,
//                        topEnd = 30.dp
//                    )
//                )
//                .padding(horizontal = 80.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//            Column(
//                verticalArrangement = Arrangement.spacedBy(40.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                when (uiState) {
//                    is UiState.Success -> { navController?.navigate(Routes.Groups.route) }
//                    is UiState.Failure -> {}
//                    is UiState.Loading -> { LoadingProgressBar(modifier = modifier) }
//                    else -> {}
//                }
//
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    AuthOutlinedTextField(
//                        value = requireNotNull(login),
//                        onValueChange = viewModel::onLoginChanged,
//                        label = stringResource(R.string.login),
//                        leadingIcon = Icons.Default.Person
//                    )
//
//                    AuthOutlinedTextField(
//                        value = requireNotNull(password),
//                        onValueChange = viewModel::onPasswordChanged,
//                        label = stringResource(R.string.password),
//                        leadingIcon = Icons.Default.Lock,
//                        visualTransformation = PasswordVisualTransformation(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//                    )
//                }
//
//                Button(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                        .border(
//                            width = 2.dp,
//                            color = DarkOrange,
//                            shape = RoundedCornerShape(12.dp)
//                        )
//                        .shadow(
//                            elevation =
//                            if(!login.isNullOrEmpty() && !password.isNullOrEmpty()) 4.dp
//                            else 0.dp,
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    shape = RoundedCornerShape(12.dp),
//                    onClick = { viewModel.login() },
//                    enabled = !login.isNullOrEmpty() && !password.isNullOrEmpty(),
//                    colors = ButtonDefaults.buttonColors(
//                        disabledContainerColor = White,
//                        disabledContentColor = DarkGreen,
//                        containerColor = DarkOrange,
//                        contentColor = White
//                    )
//                ) {
//                    Text(
//                        text = stringResource(R.string.authorize).uppercase(),
//                        style = TextStyle(
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center
//                        )
//                    )
//                }
//            }
//        }
//    }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true, locale = "ru"
)
@Composable
fun LogInPreview() {
    LogInScreen()
}