package ru.mirea.adminachu.usecase.user.dto

import ru.mirea.adminachu.domain.common.definition.UserId

data class DisableUserInfo(
    val userId: UserId,
)
