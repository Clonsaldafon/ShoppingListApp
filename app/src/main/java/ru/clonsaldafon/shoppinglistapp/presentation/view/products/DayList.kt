package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.data.model.Product
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black

@Composable
fun DayList(
    groupId: String,
    date: String,
    products: List<Product>,
    uiState: ProductsUiState,
    onEvent: (ProductsEvent) -> Unit
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
            contentDescription = stringResource(R.string.calendar),
            tint = Black
        )

        Text(
            text = date,
            style = TextStyle(
                color = Black,
                fontSize = 14.sp
            )
        )

        IconButton(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            onClick = { expanded = !expanded }
        ) {
            Icon(
                imageVector =
                if (expanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = Black
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
            products.forEach { product ->
                ProductItem(
                    groupId = groupId,
                    productId = product.productId.toString(),
                    title = product.productName ?: "",
                    count = product.quantity ?: 1,
                    price = product.price ?: 0.0,
                    addedBy = product.addedBy ?: "",
                    boughtBy = product.boughtBy,
                    uiState = uiState,
                    onEvent = onEvent
                )
            }
        }
    }
}