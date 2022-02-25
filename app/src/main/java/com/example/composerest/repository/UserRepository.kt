package com.example.composerest.repository

import androidx.lifecycle.LiveData
import com.example.composerest.datasource.RestDataSource
import com.example.composerest.model.User
import com.example.composerest.model.UserDao
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser() : User
    suspend fun deleteUser(toDelete: User)
    fun getAllUsers() : LiveData<List<User>>
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getNewUser(): User {
        val name = dataSource.getUserName().results[0].name!!
        val location = dataSource.getUserName().results[0].location!!
        val picture = dataSource.getUserName().results[0].picture!!
        val user = User(name.first, name.last, location.city, picture.thumbnail)
        userDao.inset(user)
        return user
    }

    override suspend fun deleteUser(toDelete: User) = userDao.delete(toDelete)

    override fun getAllUsers(): LiveData<List<User>> = userDao.getAll()

}