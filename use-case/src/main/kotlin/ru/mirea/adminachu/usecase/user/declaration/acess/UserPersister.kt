package ru.mirea.adminachu.usecase.user.declaration.acess

import ru.mirea.adminachu.domain.user.User

interface UserPersister {
    fun save(user: User)
}
