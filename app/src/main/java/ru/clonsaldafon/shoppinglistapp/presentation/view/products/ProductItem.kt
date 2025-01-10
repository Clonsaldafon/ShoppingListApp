package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.LightOrange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.Red
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun ProductItem(
    groupId: String,
    productId: String,
    title: String,
    count: Int,
    price: Double = 0.0,
    infoVisibility: Boolean = false,
    addedBy: String,
    boughtBy: String? = null,
    uiState: ProductsUiState,
    onEvent: (ProductsEvent) -> Unit
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
                color = if (!boughtBy.isNullOrEmpty()) White else LightOrange,
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
                            color = Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    IconButton(
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp),
                        onClick = { isInfoHidden.value = !isInfoHidden.value }
                    ) {
                        Icon(
                            imageVector =
                            if (isInfoHidden.value)
                                Icons.Filled.KeyboardArrowDown
                            else
                                Icons.Filled.KeyboardArrowUp,
                            contentDescription = null,
                            tint = DarkGray
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
                            color = DarkGray,
                            fontSize = 11.sp
                        )
                    )

                    if (boughtBy.isNullOrEmpty()) {
                        IconButton(
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp),
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = stringResource(R.string.edit),
                                tint = Orange
                            )
                        }
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Checkbox(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    checked = !boughtBy.isNullOrEmpty(),
                    onCheckedChange = {
                        onEvent(ProductsEvent.OnCurrentProductIdUpdated(productId))
                        onEvent(ProductsEvent.OnBuyWindowVisibilityChanged(false))
                        onEvent(ProductsEvent.OnCurrentQuantityUpdated(count))
                        onEvent(ProductsEvent.OnCurrentProductBought(true))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = DarkGray
                    ),
                    enabled = price == 0.0
                )

                IconButton(
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp),
                    onClick = { onEvent(ProductsEvent.OnProductDeleted(groupId, productId)) }
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize(),
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = stringResource(R.string.delete),
                        tint = Red
                    )
                }
            }
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
                            text = stringResource(R.string.added_by),
                            style = TextStyle(
                                color = DarkGray,
                                fontSize = 11.sp
                            )
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = addedBy,
                                style = TextStyle(
                                    color = Black,
                                    fontSize = 11.sp
                                )
                            )

                            Image(
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp),
                                imageVector = Icons.Default.Person,
                                contentDescription = stringResource(R.string.user)
                            )
                        }
                    }

                    if (!boughtBy.isNullOrEmpty()) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.bought_by),
                                style = TextStyle(
                                    color = DarkGray,
                                    fontSize = 11.sp
                                )
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = boughtBy,
                                    style = TextStyle(
                                        color = Black,
                                        fontSize = 11.sp
                                    )
                                )

                                Image(
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp),
                                    imageVector = Icons.Default.Person,
                                    contentDescription = stringResource(R.string.user)
                                )
                            }
                        }
                    }
                }

                if (!boughtBy.isNullOrEmpty()) {
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