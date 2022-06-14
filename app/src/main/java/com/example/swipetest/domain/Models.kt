package com.example.swipetest.domain

data class Profile(
    val id: Int,
    val firstName: String = "",
    val lastName: String = "",
    val age: Int = 21,
    val city: String = "",
    val country: String = "",
    val isMatch: Boolean,
    val photosURL: List<String>,
)