package ru.mirea.adminachu.usecase.user.dto

import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.domain.user.User

data class UpdateUserInfo(
    val userId: UserId,
    val userName: User.Name,
    val email: User.Email,
    val password: User.Password,
    val activated: User.ActivationStatus,
)
