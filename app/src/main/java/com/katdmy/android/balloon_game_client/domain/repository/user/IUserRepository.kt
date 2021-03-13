package com.katdmy.android.balloon_game_client.domain.repository.user

import com.katdmy.android.balloon_game_client.domain.repository.entity.UserEntity

interface IUserRepository {
    /**
     * Создаст пользователя на сервере
     */
    suspend fun createUser(name: String): UserEntity

    suspend fun saveUser(user: UserEntity)
    suspend fun getUserInfoBy(id: String): UserEntity
    suspend fun getActiveUser(): UserEntity?
}