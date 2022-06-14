package com.example.swipetest.ui.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDownAlt
import androidx.compose.material.icons.filled.ThumbUpAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.swipetest.domain.Profile
import com.example.swipetest.moveTo
import com.example.swipetest.visible

@ExperimentalMaterialApi
@Composable
fun CardStack(
    modifier: Modifier = Modifier,
    profiles: MutableList<Profile>,
    thresholdConfig: (Float, Float) -> ThresholdConfig = { _, _ -> FractionalThreshold(0.2f) },
    velocityThreshold: Dp = 125.dp,
    enableButtons: Boolean = false,
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
            .background(Color.Transparent, shape = RoundedCornerShape(20.dp))
    ) {
        val (buttons, stack) = createRefs()

        if (enableButtons) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(buttons) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(stack.bottom)
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = { if (lastItemIndex >= 0) cardStackController.swipeLeft() },
                    backgroundColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(5.dp)
                ) {
                    Icon(Icons.Filled.ThumbDownAlt, contentDescription = "", tint = Color.Red)
                }
                Spacer(modifier = Modifier.width(70.dp))
                FloatingActionButton(
                    onClick = { if (lastItemIndex >= 0) cardStackController.swipeRight() },
                    backgroundColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(5.dp)
                ) {
                    Icon(Icons.Filled.ThumbUpAlt, contentDescription = "", tint = Color.Green)
                }
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
//                        .shadow(4.dp, RoundedCornerShape(10.dp)),
                    item
                )
            }
        }
    }
}

@Composable
fun Card(modifier: Modifier = Modifier, profile: Profile) {
    Box(modifier) {
        AsyncImage(
            model = profile.photosURL.first(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        ) {
            Text(
                text = profile.firstName + ", " + profile.age,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                modifier = Modifier.clickable(onClick = {}) // disable the highlight of the text when dragging
            )
            Text(
                text = profile.city + ", " + profile.country,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.clickable(onClick = {}) // disable the highlight of the text when dragging
            )
        }
    }
}
