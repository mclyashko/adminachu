package ru.mirea.adminachu.usecase.user.declaration.command

import ru.mirea.adminachu.usecase.user.dto.AddUserInfo
import ru.mirea.adminachu.usecase.user.dto.UserInfo

interface AddNewUser {
    fun execute(addUserInfo: AddUserInfo): UserInfo
}
