package com.kevin.welltrack23.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.welltrack23.model.User
import com.kevin.welltrack23.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var loggedInUser: ((User?) -> Unit)? = null

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn:StateFlow<Boolean> get() = _isUserLoggedIn
     fun registerUser(user: User) {
        viewModelScope.launch {
            repository.registerUser(user)
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.loginUser(email, password)
            if (user != null) {
                _isUserLoggedIn.value = true // Update login state
                loggedInUser?.invoke(user)
            } else {
                _isUserLoggedIn.value = false
                loggedInUser?.invoke(null)
            }
        }
    }}