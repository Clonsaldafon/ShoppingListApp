package ru.clonsaldafon.shoppinglistapp.presentation.view.products.add

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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun AddProductScreen(
    navController: NavController? = null,
    viewModel: AddProductViewModel = hiltViewModel(),
    groupId: String?,
    groupName: String?,
    groupDescription: String?,
    code: String?
) {
    val uiState by viewModel.uiState.collectAsState()

    viewModel.setGroupId(groupId ?: "0")

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
        if (uiState.isLoading || uiState.isSubmitting) {
            CircularProgressIndicator(
                color = Orange
            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.product_adding),
                    style = TextStyle(
                        color = DarkGray,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    CategoriesMenu(
                        category = uiState.category,
                        categories = uiState.categories?.sortedBy { it.name } ?: listOf(),
                        onEvent = viewModel::onEvent
                    )

                    ProductsMenu(
                        product = uiState.product,
                        products = uiState.products?.sortedBy { it.name } ?: listOf(),
                        onEvent = viewModel::onEvent
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        val maxQuantity = 1000

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
                            value = uiState.quantity,
                            onValueChange = { value ->
                                if (value.isEmpty())
                                    viewModel.onEvent(AddProductEvent.OnQuantityChanged(value))
                                else if (value.isNotEmpty() &&
                                    value.all { it.isDigit() } &&
                                    value[0] != '0' &&
                                    value.toInt() <= maxQuantity)
                                    viewModel.onEvent(AddProductEvent.OnQuantityChanged(value))
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
                                        imageVector =ImageVector.vectorResource(
                                            R.drawable.ic_count
                                        ),
                                        contentDescription = stringResource(R.string.quantity),
                                        tint = DarkGray
                                    )

                                    Text(
                                        text = stringResource(R.string.quantity),
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
                                focusedTextColor = DarkGray,
                                focusedIndicatorColor = Color.Transparent,
                                errorContainerColor = White,
                                errorIndicatorColor = Color.Red,
                                cursorColor = Orange
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            singleLine = true,
                            isError = uiState.isQuantityInvalid
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 5.dp,
                                    end = 5.dp
                                ),
                            text =
                            if (uiState.isQuantityInvalid) uiState.quantityErrorMessage
                            else "не более $maxQuantity шт",
                            style = TextStyle(
                                color = if (uiState.isQuantityInvalid) Red else Color.LightGray,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        )
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
                            text = stringResource(R.string.cancel),
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
                                AddProductEvent.OnSubmit(
                                    uiState.productId,
                                    uiState.quantity
                                ) { isSuccess, quantityErrorMessage ->
                                    if (isSuccess == true)
                                        navController?.navigate(
                                            Routes.Products.createRoute(
                                                groupId, groupName, groupDescription, code
                                            )
                                        )

                                    if (!quantityErrorMessage.isNullOrEmpty())
                                        viewModel.setQuantityErrorMessage(quantityErrorMessage)
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
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    locale = "ru"
)
@Composable
fun AddProductPreview() {
    AddProductScreen(
        groupId = "0",
        groupName = "test",
        groupDescription = "test",
        code = "test"
    )
}