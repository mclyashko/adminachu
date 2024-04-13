package ru.mirea.adminachu.db.user.acess

import org.springframework.stereotype.Repository
import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.domain.user.declaration.UserIdGenerator
import java.util.UUID

@Repository
class UserIdGeneratorImpl : UserIdGenerator {
    override fun generate(): UserId {
        val id = UUID.randomUUID()
        return UserId(id)
    }
}
