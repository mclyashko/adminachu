package ru.mirea.adminachu.usecase.user.declaration.query

import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.dto.UserInfo

interface GetUserByEmail {
    fun execute(email: User.Email): UserInfo
}
