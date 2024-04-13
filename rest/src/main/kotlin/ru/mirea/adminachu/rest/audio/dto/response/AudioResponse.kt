package ru.mirea.adminachu.rest.audio.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant
import java.util.UUID

@Schema(description = "Model for the audio.")
data class AudioResponse(
    @field:Schema(
        description = "Audio ID",
        example = "3",
        type = "uuid",
    )
    val id: UUID,
    @field:Schema(
        description = "Audio name",
        example = "Song",
        type = "string",
    )
    val name: String,
    @field:Schema(
        description = "Audio description",
        example = "Good song",
        type = "string",
    )
    val description: String,
    @field:Schema(
        description = "Audio file",
        example = "[0, 1, 0]",
        type = "byte array",
    )
    val file: ByteArray,
    @field:Schema(
        description = "Creator ID",
        example = "3",
        type = "uuid",
    )
    val createdBy: UUID,
    @field:Schema(
        description = "Audio removed by",
        type = "UUID",
    )
    val removedBy: UUID?,
    @field:Schema(description = "Broadcasts")
    val broadcasts: List<Broadcast>,
    @field:Schema(description = "Sequences")
    val sequences: List<Sequence>,
) {

    data class Broadcast(
        @field:Schema(
            description = "Broadcast ID",
            example = "3",
            type = "uuid",
        )
        val id: UUID,
        @field:Schema(
            description = "Broadcast time",
            type = "instant",
        )
        val streamedAt: Instant,
    )

    data class Sequence(
        @field:Schema(
            description = "Sequence ID",
            example = "3",
            type = "uuid",
        )
        val id: UUID,
        @field:Schema(
            description = "Sequence order",
            example = "3",
            type = "long",
        )
        val order: Long,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioResponse

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (!file.contentEquals(other.file)) return false
        if (createdBy != other.createdBy) return false
        if (removedBy != other.removedBy) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + file.contentHashCode()
        result = 31 * result + createdBy.hashCode()
        result = 31 * result + (removedBy?.hashCode() ?: 0)
        return result
    }
}
