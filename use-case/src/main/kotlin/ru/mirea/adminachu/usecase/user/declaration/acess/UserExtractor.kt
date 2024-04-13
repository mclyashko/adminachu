package ru.mirea.adminachu.usecase.user.declaration.acess

import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.domain.user.User

interface UserExtractor {
    fun getById(id: UserId): User?

    fun getByUserName(userName: User.Name): User?

    fun getByEmail(email: User.Email): User?

    fun getAll(): List<User>
}
