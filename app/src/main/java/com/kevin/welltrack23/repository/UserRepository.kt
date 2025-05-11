package com.kevin.welltrack23.repository


import com.kevin.welltrack23.data.UserDao
import com.kevin.welltrack23.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}