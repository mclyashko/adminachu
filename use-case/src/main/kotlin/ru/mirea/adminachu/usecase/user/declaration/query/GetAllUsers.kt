package ru.mirea.adminachu.usecase.user.declaration.query

import ru.mirea.adminachu.usecase.user.dto.UserInfo

interface GetAllUsers {
    fun execute(): List<UserInfo>
}
