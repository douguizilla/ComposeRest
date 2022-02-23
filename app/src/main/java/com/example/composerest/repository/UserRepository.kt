package com.example.composerest.repository

import com.example.composerest.datasource.RestDataSource
import com.example.composerest.model.User
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser() : User
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource
) : UserRepository {

    override suspend fun getNewUser() : User {
        val name = dataSource.getUserName().results[0].name!!
        val location = dataSource.getUserName().results[0].location!!
        val picture = dataSource.getUserName().results[0].picture!!
        val user = User(name.first, name.last, location.city, picture.thumbnail)
        return user
    }

}