package ru.mirea.adminachu.usecase.user.declaration.query

import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.dto.UserInfo

interface GetUserByUserName {
    fun execute(userName: User.Name): UserInfo
}
