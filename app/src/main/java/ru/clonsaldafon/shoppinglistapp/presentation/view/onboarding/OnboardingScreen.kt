package ru.clonsaldafon.shoppinglistapp.presentation.view.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.Routes
import ru.clonsaldafon.shoppinglistapp.ui.theme.Black
import ru.clonsaldafon.shoppinglistapp.ui.theme.DarkGray
import ru.clonsaldafon.shoppinglistapp.ui.theme.Orange
import ru.clonsaldafon.shoppinglistapp.ui.theme.White

@Composable
fun OnboardingScreen(
    navController: NavController? = null,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pageIndex by viewModel.pageIndex.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(DarkGray, Black)
                )
            )
    ) {
        val onboardingItems = listOf(
            Onboarding(
                title = "Вместе проще!",
                description = "Создавайте группы с друзьями\n" +
                        "и семьей, чтобы легко планировать\n" +
                        "покупки и управлять финансами"
            ),
            Onboarding(
                title = "Составляйте\n" +
                        "списки покупок",
                description = "Вносите продукты в общий список, чтобы\n" +
                        "ничего не забыть при походе в магазин.\n" +
                        "Удобство для всех участников группы!"
            ),
            Onboarding(
                title = "Финансы\n" +
                        "под контролем",
                description = "Ведите совместный бюджет,\n" +
                        "отслеживайте расходы и доходы.\n" +
                        "Все прозрачно и доступно\n" +
                        "каждому участнику группы",
            )
        )

        OnboardingItem(
            navController = navController,
            title = onboardingItems[pageIndex!!].title,
            description = onboardingItems[pageIndex!!].description,
            selectedIndex = pageIndex!!,
            onNextPageClick = viewModel::increaseIndex,
            onPreviousPageClick = viewModel::decreaseIndex
        )
    }
}

private data class Onboarding(
    val title: String,
    val description: String
)

@Composable
fun OnboardingItem(
    navController: NavController?,
    title: String,
    description: String,
    selectedIndex: Int,
    onNextPageClick: () -> Unit,
    onPreviousPageClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 70.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )

        Image(
            bitmap =
                when (selectedIndex) {
                    0 -> ImageBitmap.imageResource(R.drawable.onboarding_1)
                    1 -> ImageBitmap.imageResource(R.drawable.onboarding_2)
                    else -> ImageBitmap.imageResource(R.drawable.onboarding_3)
                },
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = description,
                style = TextStyle(
                    color = White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )

            if (selectedIndex == 2) {
                Button(
                    modifier = Modifier
                        .shadow(
                            elevation = 15.dp,
                            spotColor = Orange,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .background(
                            color = Orange,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    onClick = { navController?.navigate(Routes.SignUp.route) },
                    contentPadding = PaddingValues(
                        vertical = 15.dp,
                        horizontal = 30.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange,
                        contentColor = White
                    )
                ) {
                    Text(
                        text = "Начать".uppercase(),
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    onClick = { if (selectedIndex > 0) onPreviousPageClick() },
                    enabled = selectedIndex > 0
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize(),
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = if (selectedIndex == 0) Color.Transparent else White
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_ellipse),
                        contentDescription = null,
                        tint = if (selectedIndex == 0) Orange else White
                    )

                    Icon(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_ellipse),
                        contentDescription = null,
                        tint = if (selectedIndex == 1) Orange else White
                    )

                    Icon(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_ellipse),
                        contentDescription = null,
                        tint = if (selectedIndex == 2) Orange else White
                    )
                }

                IconButton(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    onClick = { if (selectedIndex < 2) onNextPageClick() },
                    enabled = selectedIndex < 2
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize(),
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = if (selectedIndex == 2) Color.Transparent else White
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun OnboardingPreview() {
    OnboardingScreen()
}