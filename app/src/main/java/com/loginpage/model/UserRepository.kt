package com.loginpage.model

class UserRepository(private val userDao: UserDao) {
    suspend fun login(username: String, password: String): Boolean {
        val user = userDao.checkUser(username, password)
        return user != null
    }

    suspend fun insertDummyUser() {
        userDao.insertUser(UserEntity(username = "admin", password = "password123"))
    }
}