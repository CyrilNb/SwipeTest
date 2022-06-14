package com.example.swipetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.swipetest.domain.Profile
import com.example.swipetest.ui.composables.CardStack
import com.example.swipetest.ui.composables.Header
import com.example.swipetest.ui.theme.ClubstestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClubstestTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    MainView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainView() {
    val profile1 = Profile(
        id = 1,
        firstName = "Courtney",
        lastName = "Roman",
        city = "Santa Monica",
        country = "USA 🇺🇸",
        isMatch = false,
        photosURL = listOf(
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Flady_01.jpg?alt=media&token=b0ad737b-a92c-42a6-95c3-fd2b82512f91",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Flady_02.jpg?alt=media&token=f4c7b08a-612f-45a8-9cc6-7820a18ac3d9",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Flady_03.jpg?alt=media&token=7a807583-b12b-4a72-a7eb-b472d0176629",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Flady_04.jpg?alt=media&token=da7229cb-2728-49f1-8079-32f3bafb95e2"
        )
    )

    val profile2 = Profile(
        id = 2,
        firstName = "Wilson",
        lastName = "Webb",
        city = "Brooklyn",
        country = "USA 🇺🇸",
        isMatch = true,
        photosURL = listOf(
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fbeard_01.jpg?alt=media&token=eb86efb5-3362-4b83-be57-d6dc1ba5246f",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fbeard_02.jpg?alt=media&token=f347272e-e6b4-4917-b281-583f7795dd9b",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fbeard_03.jpg?alt=media&token=0b43732c-ac38-464a-badd-9a47f4e0f33c",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fbeard_04.jpg?alt=media&token=8ec3923c-ca6a-41d2-b485-4d1fbdbba11e"
        )
    )

    val profile3 = Profile(
        id = 3,
        firstName = "Brandi",
        lastName = "Leigh",
        city = "Orange County",
        country = "USA 🇺🇸",
        isMatch = true,
        photosURL = listOf(
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fwoman_03.jpg?alt=media&token=b7474805-7e96-471b-a122-39e1ebdd2b1f",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fwoman_02.jpg?alt=media&token=bf11c594-1d65-4153-9016-763bdb791dee",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fwoman_01.jpg?alt=media&token=4828a760-a617-471c-b7b9-9aff9e52448d"
        )
    )

    val profile4 = Profile(
        id = 4,
        firstName = "Kody",
        lastName = "Day",
        city = "Santa Cruz",
        country = "USA 🇺🇸",
        isMatch = false,
        photosURL = listOf(
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fdude_01.jpg?alt=media&token=92774d10-ef37-4c36-8d1d-e151871b6ea2",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fdude_02.jpg?alt=media&token=a37a38dc-f4ac-4e8e-a9eb-1678d037d21a",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fdude_03.jpg?alt=media&token=6140fa77-6a76-440a-b8bb-98f4087845d1",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Fdude_04.jpg?alt=media&token=b1cf6287-a143-4206-8406-b53e4b4715ff"
        )
    )

    val profile5 = Profile(
        id = 5,
        firstName = "Kylie",
        lastName = "Cruz",
        city = "Washington",
        country = "USA 🇺🇸",
        isMatch = true,
        photosURL = listOf(
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Ffemale_02.jpg?alt=media&token=4312818f-1c36-48f7-90fc-17dbe956997e",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Ffemale_01.jpg?alt=media&token=73dd6d6d-0b8a-4843-839b-3f0394927ce0",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Ffemale_03.jpg?alt=media&token=2aae0b41-5c3f-4551-9eaf-135643169607",
            "https://firebasestorage.googleapis.com/v0/b/plugcoinprod.appspot.com/o/takehomeAssets%2Ffemale_04.jpg?alt=media&token=22992f47-d837-4726-9760-1d2ec82cbaae"
        )
    )

    val profiles = remember { mutableListOf(profile1, profile2, profile3, profile4, profile5) }

    val matchesCounter: MutableState<Int> = remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize()) {

        Header(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 0.dp),
            matchesCounter = matchesCounter
        )

        CardStack(
            modifier = Modifier,
            profiles = profiles,
            onSwipeRight = {
                if (it.isMatch) {
                    matchesCounter.value++
                }
            },
        )
    }
}