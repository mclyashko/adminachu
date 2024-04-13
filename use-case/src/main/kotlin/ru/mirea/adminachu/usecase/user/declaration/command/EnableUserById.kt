package ru.mirea.adminachu.usecase.user.declaration.command

import ru.mirea.adminachu.usecase.user.dto.EnableUserInfo
import ru.mirea.adminachu.usecase.user.dto.UserInfo

interface EnableUserById {
    fun execute(enableUserInfo: EnableUserInfo): UserInfo
}
