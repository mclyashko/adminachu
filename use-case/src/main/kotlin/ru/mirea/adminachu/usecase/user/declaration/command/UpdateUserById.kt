package ru.mirea.adminachu.usecase.user.declaration.command

import ru.mirea.adminachu.usecase.user.dto.UpdateUserInfo
import ru.mirea.adminachu.usecase.user.dto.UserInfo

interface UpdateUserById {
    fun execute(updateUserInfo: UpdateUserInfo): UserInfo
}
