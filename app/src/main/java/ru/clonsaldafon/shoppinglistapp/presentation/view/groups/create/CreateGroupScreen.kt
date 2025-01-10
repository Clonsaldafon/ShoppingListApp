package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.create

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import okhttp3.Route
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun CreateGroupScreen(
    navController: NavController? = null,
    viewModel: CreateGroupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = White
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
                verticalArrangement = Arrangement.spacedBy(70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Создание группы",
                    style = TextStyle(
                        color = DarkGray,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Column {
                        val maxLength = 30

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
                            value = uiState.name,
                            onValueChange = {
                                if (it.length <= maxLength)
                                    viewModel.onEvent(CreateGroupEvent.OnNameChanged(it))
                            },
                            placeholder = {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .width(15.dp)
                                            .height(15.dp),
                                        imageVector = ImageVector.vectorResource(
                                            R.drawable.ic_bookmark
                                        ),
                                        contentDescription = null,
                                        tint = DarkGray
                                    )

                                    Text(
                                        text = "Название",
                                        style = TextStyle(
                                            color = DarkGray,
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = White,
                                disabledTextColor = DarkGray,
                                disabledIndicatorColor = Color.Transparent,
                                unfocusedContainerColor = White,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = White,
                                focusedIndicatorColor = Color.Transparent,
                                errorContainerColor = White,
                                errorIndicatorColor = Color.Red,
                                cursorColor = Orange
                            ),
                            singleLine = true,
                            isError = uiState.isNameInvalid
                        )

                        if (uiState.nameErrorMessage.isNotEmpty()) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 5.dp,
                                        end = 5.dp
                                    ),
                                text = uiState.nameErrorMessage,
                                style = TextStyle(
                                    color = Red,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 5.dp,
                                        end = 5.dp
                                    ),
                                text =
                                if(uiState.name.length >= maxLength - 5)
                                    "${uiState.name.length} / $maxLength"
                                else
                                    "",
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Right
                                )
                            )
                        }
                    }

                    Column {
                        val maxLength = 255

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .background(
                                    color = White,
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            value = uiState.description,
                            onValueChange = {
                                if (it.length <= maxLength)
                                    viewModel.onEvent(CreateGroupEvent.OnDescriptionChanged(it))
                            },
                            placeholder = {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .width(15.dp)
                                            .height(15.dp),
                                        imageVector = ImageVector.vectorResource(
                                            R.drawable.ic_description
                                        ),
                                        contentDescription = null,
                                        tint = DarkGray
                                    )

                                    Text(
                                        text = "Описание",
                                        style = TextStyle(
                                            color = DarkGray,
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = White,
                                disabledTextColor = DarkGray,
                                disabledIndicatorColor = Color.Transparent,
                                unfocusedContainerColor = White,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = White,
                                focusedIndicatorColor = Color.Transparent,
                                errorContainerColor = White,
                                errorIndicatorColor = Color.Red,
                                cursorColor = Orange
                            ),
                            isError = uiState.isDescriptionInvalid
                        )

                        if (uiState.descriptionErrorMessage.isNotEmpty()) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 5.dp,
                                        end = 5.dp
                                    ),
                                text = uiState.nameErrorMessage,
                                style = TextStyle(
                                    color = Red,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 5.dp,
                                        end = 5.dp
                                    ),
                                text =
                                if(uiState.description.length >= maxLength - 25)
                                    "${uiState.description.length} / $maxLength"
                                else
                                    "",
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Right
                                )
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(
                                color = White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = DarkGray,
                                shape = RoundedCornerShape(15.dp)
                            ),
                        onClick = { navController?.popBackStack() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = White,
                            contentColor = DarkGray
                        ),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "Отменить",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
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
                                CreateGroupEvent.OnSubmit(
                                    uiState.name,
                                    uiState.description
                                ) { isSuccess, nameErrorMessage, descriptionErrorMessage ->
                                    if (isSuccess == true)
                                        navController?.navigate(Routes.Groups.route)

                                    if (!nameErrorMessage.isNullOrEmpty())
                                        viewModel.setNameErrorMessage(nameErrorMessage)

                                    if (!descriptionErrorMessage.isNullOrEmpty())
                                        viewModel.setDescriptionErrorMessage(
                                            descriptionErrorMessage
                                        )
                                }
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
                            text = "Создать",
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    locale = "ru"
)
@Composable
fun CreateGroupPreview() {
    CreateGroupScreen()
}