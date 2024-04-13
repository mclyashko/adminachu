package ru.mirea.adminachu.db.audio.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("sequence")
data class SequenceEntity(
    @Id
    @JvmField
    val id: UUID,
    val order: Long,
    val audioId: UUID,
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
