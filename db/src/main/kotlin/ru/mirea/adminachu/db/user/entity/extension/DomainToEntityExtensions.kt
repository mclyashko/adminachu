package ru.mirea.adminachu.db.user.entity.extension

import ru.mirea.adminachu.db.user.entity.UserEntity
import ru.mirea.adminachu.domain.user.User

fun User.toUserEntity(): UserEntity = UserEntity(
    id = id.toUUIDValue(),
    userName = userName.toStringValue(),
    email = email.toStringValue(),
    password = password.toStringValue(),
    activated = activated.toBooleanValue(),
)
