package ru.clonsaldafon.shoppinglistapp.presentation.view.signup

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
import ru.clonsaldafon.shoppinglistapp.presentation.component.SignUpOutlinedTextField
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.Blue
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Pink
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
                if (uiState.isSubmitting) {
                    CircularProgressIndicator(
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

                        var expanded by remember { mutableStateOf(false) }
                        val genders = listOf(
                            stringResource(R.string.male),
                            stringResource(R.string.female)
                        )

                        Column {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .border(
                                        width = 1.dp,
                                        color = DarkGray,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .clickable { expanded = !expanded },
                                shape = RoundedCornerShape(15.dp),
                                value = if (uiState.gender != "") uiState.gender else genders[0],
                                textStyle = TextStyle(
                                    color = Black,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                ),
                                enabled = false,
                                onValueChange = {
                                    viewModel.onEvent(SignUpEvent.OnGenderChanged(it))
                                },
                                leadingIcon = {
                                    Column(
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                            .background(
                                                color = DarkGray,
                                                shape = RoundedCornerShape(
                                                    topStart = 15.dp,
                                                    bottomStart = 15.dp
                                                )
                                            ),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .width(30.dp)
                                                .height(30.dp),
                                            imageVector =
                                            if (uiState.gender == ""
                                                || uiState.gender == stringResource(R.string.male))
                                                ImageVector.vectorResource(R.drawable.ic_male)
                                            else
                                                ImageVector.vectorResource(R.drawable.ic_female),
                                            tint = White,
                                            contentDescription = null
                                        )
                                    }
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector =
                                        if (expanded)
                                            Icons.Filled.KeyboardArrowUp
                                        else
                                            Icons.Filled.KeyboardArrowDown,
                                        contentDescription = null,
                                        tint = DarkGray
                                    )
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    disabledBorderColor = Color.Transparent
                                )
                            )

                            DropdownMenu(
                                modifier = Modifier
                                    .background(
                                        color = White
                                    ),
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                genders.forEach { gender ->
                                    DropdownMenuItem(
                                        onClick = {
                                            expanded = false
                                            viewModel.onEvent(SignUpEvent.OnGenderChanged(gender))
                                        },
                                        text = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                                            ) {
                                                val imageVector =
                                                    if (gender == stringResource(R.string.male))
                                                        ImageVector.vectorResource(R.drawable.ic_male)
                                                    else
                                                        ImageVector.vectorResource(R.drawable.ic_female)
                                                val tint =
                                                    if (gender == stringResource(R.string.male))
                                                        Blue
                                                    else
                                                        Pink

                                                Icon(
                                                    imageVector = imageVector,
                                                    contentDescription = null,
                                                    tint = tint
                                                )

                                                Text(
                                                    text = gender,
                                                    style = TextStyle(
                                                        color = DarkGray,
                                                        fontSize = 14.sp
                                                    )
                                                )
                                            }
                                        }
                                    )
                                }
                            }

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 5.dp,
                                        end = 5.dp
                                    ),
                                text = "",
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Right
                                )
                            )
                        }

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
                                    SignUpEvent.OnSubmit(
                                        username = uiState.login,
                                        password = uiState.password,
                                        gender = uiState.gender
                                    ) { tokenResponse, loginErrorMessage ->
                                        if (tokenResponse != null)
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
                                        text = "Создать аккаунт".uppercase(),
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    )

                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        tint = if (uiState.isValid) White else DarkGray
                                    )
                                }
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "Уже есть аккаунт?",
                                style = TextStyle(
                                    color = Black,
                                    fontSize = 14.sp
                                )
                            )

                            Text(
                                modifier = Modifier
                                    .clickable { navController?.navigate(Routes.LogIn.route) }
                                    .padding(2.dp),
                                text = "Войти",
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