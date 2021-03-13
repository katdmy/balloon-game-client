package com.katdmy.android.balloon_game_client.rooms.presentation

sealed class LoginState {
    object False : LoginState()
    data class True(
        val username: String,
        val userId: String
    ) : LoginState()
}