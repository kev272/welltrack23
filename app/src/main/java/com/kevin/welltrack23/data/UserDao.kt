package com.kevin.welltrack23.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kevin.welltrack23.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun loginUser(email: String, password: String): User?

    // Non-suspend version for Java interoperability
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    fun loginUserBlocking(email: String, password: String): User?
}