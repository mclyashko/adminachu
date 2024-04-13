package ru.mirea.adminachu.rest.user.dto.response.extension

import ru.mirea.adminachu.rest.user.dto.response.UserResponse
import ru.mirea.adminachu.usecase.user.dto.UserInfo

fun UserInfo.toResponseDto(): UserResponse = UserResponse(
    id = id.toUUIDValue(),
    userName = userName.toStringValue(),
    email = email.toStringValue(),
    password = password.toStringValue(),
    activated = activated.toBooleanValue(),
)
