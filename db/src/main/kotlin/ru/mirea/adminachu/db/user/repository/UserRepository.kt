package ru.mirea.adminachu.db.user.repository

import org.springframework.data.repository.CrudRepository
import ru.mirea.adminachu.db.user.entity.UserEntity
import java.util.Optional
import java.util.UUID

interface UserRepository : CrudRepository<UserEntity, UUID> {
    fun findByUserName(userName: String): Optional<UserEntity>

    fun findByEmail(email: String): Optional<UserEntity>
}
