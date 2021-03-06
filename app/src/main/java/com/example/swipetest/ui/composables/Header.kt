package com.example.swipetest.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.swipetest.R
import com.example.swipetest.ui.theme.Pink

@Composable
fun Header(modifier: Modifier, matchesCounter: MutableState<Int>) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(20.dp)
                .height(22.dp),
            painter = painterResource(R.drawable.ic_settings),
            contentDescription = "",
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.img_branding),
            contentDescription = "",
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .padding(end = 20.dp)
                .offset(x = (-14).dp)
                .drawBehind {
                    drawCircle(
                        color = Pink,
                        radius = this.size.maxDimension
                    )
                },
            text = matchesCounter.value.toString(),
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
        )
    }
}