package ru.mirea.adminachu.usecase.user.declaration.command

import ru.mirea.adminachu.usecase.user.dto.DisableUserInfo
import ru.mirea.adminachu.usecase.user.dto.UserInfo

interface DisableUserById {
    fun execute(disableUserInfo: DisableUserInfo): UserInfo
}
