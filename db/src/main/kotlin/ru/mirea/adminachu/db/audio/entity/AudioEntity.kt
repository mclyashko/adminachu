package ru.mirea.adminachu.db.audio.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("audio")
data class AudioEntity(
    @Id
    @JvmField
    val id: UUID,
    val name: String,
    val description: String,
    val file: ByteArray,
    val createdBy: UUID,
    val removedBy: UUID?,
) : Persistable<UUID> {

    @field:Transient
    internal var isNew = false

    override fun getId(): UUID {
        return id
    }

    override fun isNew(): Boolean {
        return isNew
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (!file.contentEquals(other.file)) return false
        if (createdBy != other.createdBy) return false
        if (removedBy != other.removedBy) return false
        if (isNew != other.isNew) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + file.contentHashCode()
        result = 31 * result + createdBy.hashCode()
        result = 31 * result + (removedBy?.hashCode() ?: 0)
        result = 31 * result + isNew.hashCode()
        return result
    }
}
