package com.example.composerest.model

data class User (
    val name: String,
    val lasName: String,
    val city: String,
    val thumbnail: String,
    var id: Int = 0
)