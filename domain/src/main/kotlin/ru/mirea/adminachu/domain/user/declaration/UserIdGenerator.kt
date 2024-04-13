package ru.mirea.adminachu.domain.user.declaration

import ru.mirea.adminachu.domain.common.definition.UserId

interface UserIdGenerator {
    fun generate(): UserId
}
