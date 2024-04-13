package ru.mirea.adminachu.usecase.user.dto

import ru.mirea.adminachu.domain.user.User

data class AddUserInfo(
    val userName: User.Name,
    val email: User.Email,
    val password: User.Password,
)
