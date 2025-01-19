package ru.clonsaldafon.shoppinglistapp.presentation.view.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import ru.clonsaldafon.shoppinglistapp.presentation.component.AuthBottomText
import ru.clonsaldafon.shoppinglistapp.presentation.component.GenderMenu
import ru.clonsaldafon.shoppinglistapp.presentation.component.LoadingProgressBar
import ru.clonsaldafon.shoppinglistapp.presentation.component.RememberMe
import ru.clonsaldafon.shoppinglistapp.presentation.component.SignUpOutlinedTextField
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

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
                        text = stringResource(R.string.registration),
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            )
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
                    )
                    .padding(
                        horizontal = 40.dp
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isSubmitting) {
                    LoadingProgressBar(
                        color = Orange
                    )
                } else {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SignUpOutlinedTextField(
                            value = uiState.login,
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                            label = stringResource(R.string.login),
                            leadingIcon = Icons.Default.Person
                        )

                        SignUpOutlinedTextField(
                            value = uiState.password,
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                            label = stringResource(R.string.password),
                            leadingIcon = Icons.Default.Lock,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )

                        GenderMenu(
                            value = uiState.gender,
                            uiState = uiState,
                            onEvent = viewModel::onEvent
                        )

                        RememberMe(
                            checked = uiState.remember,
                            onCheckedChange = {
                                viewModel.onEvent(SignUpEvent.OnRememberChanged(it))
                            }
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .border(
                                    width = 2.dp,
                                    color = Orange,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .shadow(
                                    elevation =
                                    if(uiState.isValid) 15.dp
                                    else 0.dp,
                                    shape = RoundedCornerShape(15.dp)
                                ),
                            shape = RoundedCornerShape(15.dp),
                            onClick = {
                                viewModel.onEvent(
                                    SignUpEvent.OnSubmit { tokenResponse, loginErrorMessage ->
                                        if (tokenResponse?.accessToken != null)
                                            navController?.navigate(Routes.Groups.route)

                                        if (!loginErrorMessage.isNullOrEmpty())
                                            viewModel.setLoginErrorMessage(loginErrorMessage)
                                    }
                                )
                            },
                            enabled = uiState.isValid,
                            colors = ButtonDefaults.buttonColors(
                                disabledContainerColor = White,
                                disabledContentColor = DarkGray,
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
                                        text = stringResource(R.string.create_account).uppercase(),
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    )

                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = stringResource(
                                            R.string.create_account
                                        ),
                                        tint = if (uiState.isValid) White else DarkGray
                                    )
                                }
                            }
                        }

                        AuthBottomText(
                            text = stringResource(R.string.already_have_account),
                            clickableText = stringResource(R.string.authorize),
                            onClick = { navController?.navigate(Routes.LogIn.route) }
                        )
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
fun SignUpPreview() {
    SignUpScreen()
}