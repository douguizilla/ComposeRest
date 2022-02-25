package com.example.composerest.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composerest.model.User
import com.example.composerest.model.UserDao

@Database(entities = [User::class], version = 1)
abstract class DbDataSource : RoomDatabase(){
    abstract fun userDao() : UserDao
}