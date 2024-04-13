package ru.mirea.adminachu.usecase.user.dto.extension

import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.dto.UserInfo

fun User.toUserInfoDto(): UserInfo = UserInfo(
    id = id,
    userName = userName,
    email = email,
    password = password,
    activated = activated,
)
