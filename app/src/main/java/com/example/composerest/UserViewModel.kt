package com.example.composerest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composerest.model.User
import com.example.composerest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isLoading : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val users : LiveData<List<User>> by lazy {
        userRepository.getAllUsers()
    }

    val isLoading : LiveData<Boolean> get() = _isLoading

    fun addUser(){
        if(_isLoading.value == false){
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                userRepository.getNewUser()
                _isLoading.postValue(false)
            }
        }
    }

    fun deleteUser(toDelete: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(toDelete)
        }
    }


}