package com.example.composerest.di

import com.example.composerest.datasource.RestDataSource
import com.example.composerest.model.User
import com.example.composerest.repository.UserRepository
import com.example.composerest.repository.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Provides
    abstract fun userRepository(repo: UserRepositoryImp) : UserRepository

}
