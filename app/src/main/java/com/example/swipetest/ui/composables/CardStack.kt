package com.example.swipetest.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.swipetest.R
import com.example.swipetest.domain.Profile
import com.example.swipetest.moveTo
import com.example.swipetest.ui.theme.Black
import com.example.swipetest.ui.theme.BorderColor
import com.example.swipetest.ui.theme.Green
import com.example.swipetest.ui.theme.Pink
import com.example.swipetest.visible
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun CardStack(
    modifier: Modifier = Modifier,
    profiles: MutableList<Profile>,
    thresholdConfig: (Float, Float) -> ThresholdConfig = { _, _ -> FractionalThreshold(0.2f) },
    velocityThreshold: Dp = 125.dp,
    onSwipeLeft: (profile: Profile) -> Unit = {},
    onSwipeRight: (profile: Profile) -> Unit = {},
    onEmptyStack: (lastProfile: Profile) -> Unit = {}
) {
    var lastItemIndex by remember { mutableStateOf(profiles.lastIndex) }

    if (lastItemIndex == -1) {
        onEmptyStack(profiles.last())
    }

    val cardStackController = rememberCardStackController()

    cardStackController.onSwipeLeft = {
        onSwipeLeft(profiles[lastItemIndex])
        lastItemIndex--
    }

    cardStackController.onSwipeRight = {
        onSwipeRight(profiles[lastItemIndex])
        lastItemIndex--
    }

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {
        val (buttons, stack) = createRefs()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(buttons) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(stack.bottom)
                }
                .offset(y = (-25).dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            FloatingActionButton(
                onClick = { if (lastItemIndex >= 0) cardStackController.swipeLeft() },
                backgroundColor = Pink,
                elevation = FloatingActionButtonDefaults.elevation(4.dp)
            ) {
                Image(
                    modifier = Modifier.size(15.dp),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = "",
                )
            }

            Spacer(modifier = Modifier.width(69.dp))

            FloatingActionButton(
                onClick = { if (lastItemIndex >= 0) cardStackController.swipeRight() },
                backgroundColor = Green,
                elevation = FloatingActionButtonDefaults.elevation(4.dp)
            ) {
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(R.drawable.ic_validate),
                    contentDescription = "",
                )
            }
        }

        Box(modifier = Modifier
            .constrainAs(stack) { top.linkTo(parent.top) }
            .draggableStack(
                controller = cardStackController,
                thresholdConfig = thresholdConfig,
                velocityThreshold = velocityThreshold
            )
            .fillMaxHeight(0.9f)
        ) {
            profiles.asReversed().forEachIndexed { index, item ->
                Card(
                    modifier = Modifier
                        .moveTo(
                            x = if (index == lastItemIndex) cardStackController.offsetX.value else 0f,
                            y = if (index == lastItemIndex) cardStackController.offsetY.value else 0f
                        )
                        .visible(visible = index == lastItemIndex || index == lastItemIndex - 1)
                        .graphicsLayer(
                            rotationZ = if (index == lastItemIndex) cardStackController.rotation.value else 0f,
                            scaleX = if (index < lastItemIndex) cardStackController.scale.value else 1f,
                            scaleY = if (index < lastItemIndex) cardStackController.scale.value else 1f
                        ),
                    item
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Card(modifier: Modifier = Modifier, profile: Profile) {
    Box(modifier) {

        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()

        HorizontalPager(state = pagerState, count = profile.photosURL.size, userScrollEnabled = false) { photo ->
            AsyncImage(
                model = profile.photosURL[photo],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        if (currentPage != profile.photosURL.lastIndex) scope.launch {
                            pagerState.scrollToPage(currentPage + 1)
                        }
                    }
            )
        }

        //Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .background(Color.White, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .border(
                    width = 0.01.dp,
                    color = BorderColor,
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )

        ) {
            Text(
                modifier = Modifier
                    .padding(start = 18.dp, top = 13.dp)
                    .clickable(onClick = {}), // disable the highlight of the text when dragging
                text = profile.firstName + ", " + profile.age,
                color = Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                modifier = Modifier
                    .padding(start = 18.dp, bottom = 13.dp)
                    .clickable(onClick = {}), // disable the highlight of the text when dragging
                text = profile.city + ", " + profile.country,
                color = Color.Gray,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}
