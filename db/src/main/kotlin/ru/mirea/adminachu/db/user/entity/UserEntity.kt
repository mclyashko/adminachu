package ru.mirea.adminachu.db.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("user")
data class UserEntity(
    @Id
    @JvmField
    val id: UUID,
    val userName: String,
    val email: String,
    val password: String,
    val activated: Boolean,
) : Persistable<UUID> {

    @field:Transient
    internal var isNew = false

    override fun getId(): UUID {
        return id
    }

    override fun isNew(): Boolean {
        return isNew
    }
}
