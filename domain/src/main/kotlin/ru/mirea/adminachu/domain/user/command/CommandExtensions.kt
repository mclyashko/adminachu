package ru.mirea.adminachu.domain.user.command

import ru.mirea.adminachu.domain.user.User

fun User.markAsDisabled() = User.restoreUser(
    id = id,
    userName = userName,
    email = email,
    password = password,
    activated = User.ActivationStatus(value = false)
)

fun User.markAsEnabled() = User.restoreUser(
    id = id,
    userName = userName,
    email = email,
    password = password,
    activated = User.ActivationStatus(value = true)
)
