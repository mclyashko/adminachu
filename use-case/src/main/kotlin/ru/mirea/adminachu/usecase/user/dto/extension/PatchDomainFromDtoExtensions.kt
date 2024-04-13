package ru.mirea.adminachu.usecase.user.dto.extension

import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.dto.UpdateUserInfo

fun User.updateFromDto(updateUserInfo: UpdateUserInfo): User = User.restoreUser(
    id = id,
    userName = updateUserInfo.userName,
    email = updateUserInfo.email,
    password = updateUserInfo.password,
    activated = updateUserInfo.activated,
)
