package com.example.composerest.repository

import com.example.composerest.datasource.RestDataSource
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser()

}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource
) : UserRepository {

    override suspend fun getNewUser() {
        val name = dataSource.getUserName().results[0].name!!
    }

}