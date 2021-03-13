package com.katdmy.android.balloon_game_client.data.user

import com.katdmy.android.balloon_game_client.domain.repository.entity.UserEntity
import com.katdmy.android.balloon_game_client.domain.repository.user.IUserRepository

class UserRepository: IUserRepository {
    override suspend fun createUser(name: String): UserEntity {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: UserEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfoBy(id: String): UserEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getActiveUser(): UserEntity? {
        TODO("Not yet implemented")
    }
}