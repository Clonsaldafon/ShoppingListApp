package ru.clonsaldafon.shoppinglistapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange

@Composable
fun AuthBottomText(
    text: String,
    clickableText: String,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Black,
                fontSize = 14.sp
            )
        )

        Text(
            modifier = Modifier
                .clickable { onClick() }
                .padding(2.dp),
            text = clickableText,
            style = TextStyle(
                color = Orange,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}