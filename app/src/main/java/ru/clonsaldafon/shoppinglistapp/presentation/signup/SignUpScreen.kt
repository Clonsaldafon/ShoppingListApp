package ru.clonsaldafon.shoppinglistapp.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkOrange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Green
import ru.clonsaldafon.shoppinglistapp.ui.theme.ShoppingListAppTheme
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.observeAsState()
    val login by viewModel.login.observeAsState()
    val password by viewModel.password.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Green
            )
    ) {
        SignUpTitle(
            text = "Регистрация"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp
                    )
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp
                    )
                )
                .padding(
                    horizontal = 80.dp
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (uiState) {
                    is UiState.Success -> { TODO("navigation") }
                    is UiState.Failure -> {}
                    is UiState.Loading -> { LoadingProgressBar(modifier = modifier) }
                    else -> {}
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SignUpOutlinedTextField(
                        value = requireNotNull(login),
                        onValueChange = viewModel::onLoginChanged,
                        label = "Логин",
                        leadingIcon = Icons.Default.Person
                    )

                    SignUpOutlinedTextField(
                        value = requireNotNull(password),
                        onValueChange = viewModel::onPasswordChanged,
                        label = "Пароль",
                        leadingIcon = Icons.Default.Lock,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(
                            width = 2.dp,
                            color = DarkOrange,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .shadow(
                            elevation =
                                if(!login.isNullOrEmpty() && !password.isNullOrEmpty()) 4.dp
                                else 0.dp,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        viewModel.signup()
                    },
                    enabled = !login.isNullOrEmpty() && !password.isNullOrEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = White,
                        disabledContentColor = DarkGreen,
                        containerColor = DarkOrange,
                        contentColor = White
                    )
                ) {
                    Text(
                        text = "Продолжить".uppercase(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpTitle(
    text: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 70.dp,
                horizontal = 85.dp
            ),
        text = text,
        style = TextStyle(
            color = White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun SignUpOutlinedTextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLength: Int = 16
) {
    Column{
        Text(
            modifier = Modifier
                .padding(
                    bottom = 15.dp
                ),
            text = label,
            style = TextStyle(
                color = DarkGreen,
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    width = 2.dp,
                    color = Green,
                    shape = RoundedCornerShape(12.dp)
                ),
            value = value,
            onValueChange = {
                if (it.length <= maxLength) onValueChange(it)
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = DarkGreen,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            leadingIcon = {
                Column(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .background(
                            color = Green,
                            shape = RoundedCornerShape(
                                topStart = 12.dp,
                                bottomStart = 12.dp
                            )
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp),
                        imageVector = leadingIcon,
                        tint = White,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 5.dp,
                    end = 5.dp
                ),
            text =
                if(value.length >= maxLength - 5) "${value.length} / $maxLength"
                else "",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Right
            )
        )
    }
}

@Composable
fun LoadingProgressBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = DarkOrange
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun SignUpScreenPreview() {
    ShoppingListAppTheme {
        LoadingProgressBar()
    }
}