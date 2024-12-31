package ru.clonsaldafon.shoppinglistapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.ui.theme.Green

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomAppBar(
                modifier = modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    ),
                containerColor = Green,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp),
                        onClick = {
                            navController?.navigate(Routes.Groups.route)
                        }
                    ) {
                        Image(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            bitmap = ImageBitmap.imageResource(R.drawable.groups),
                            contentDescription = "Группы"
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp),
                        onClick = {}
                    ) {
                        Image(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            bitmap = ImageBitmap.imageResource(R.drawable.profile_selected),
                            contentDescription = "Профиль"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
        ) {
            Text(
                text = "Profile"
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MyPreview() {
    ProfileScreen()
}