package com.example.composerest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    val name: String,
    val lasName: String,
    val city: String,
    val thumbnail: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)