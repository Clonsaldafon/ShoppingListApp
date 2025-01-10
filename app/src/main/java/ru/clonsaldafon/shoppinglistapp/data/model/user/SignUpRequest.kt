package ru.clonsaldafon.shoppinglistapp.data.model.user

data class SignUpRequest(
    val username: String,
    val password: String,
    val gender: String
)
