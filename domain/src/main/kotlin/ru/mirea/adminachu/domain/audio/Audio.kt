@file:Suppress("LongParameterList")

package ru.mirea.adminachu.domain.audio

import ru.mirea.adminachu.domain.audio.declaration.AudioAlreadyExists
import ru.mirea.adminachu.domain.audio.declaration.AudioIdGenerator
import ru.mirea.adminachu.domain.audio.declaration.BroadcastIdGenerator
import ru.mirea.adminachu.domain.audio.declaration.SequenceIdGenerator
import ru.mirea.adminachu.domain.common.definition.AudioId
import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.domain.common.design.entity.AggregateRoot
import ru.mirea.adminachu.domain.common.design.entity.DomainEntity
import ru.mirea.adminachu.domain.common.design.entity.ValueObject
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import java.time.Instant
import java.util.UUID

class Audio internal constructor(
    id: AudioId,
    isNew: Boolean,
    val name: Name,
    val description: Description,
    val file: File,
    val createdBy: UserId,
    val removedBy: UserId?,
    val broadcasts: List<Broadcast>,
    val sequences: List<Sequence>,
) : AggregateRoot<AudioId>(id = id, isNew = isNew) {

    data class Name(
        private val value: String,
    ) : ValueObject {
        fun toStringValue(): String = value
    }

    data class Description(
        private val value: String,
    ) : ValueObject {
        fun toStringValue(): String = value
    }

    data class File(
        private val value: ByteArray,
    ) : ValueObject {
        fun toByteArrayValue(): ByteArray = value

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as File

            return value.contentEquals(other.value)
        }

        override fun hashCode(): Int {
            return value.contentHashCode()
        }
    }

    class Broadcast internal constructor(
        id: Id,
        isNew: Boolean,
        val streamedAt: StreamingTime,
    ) : DomainEntity<Broadcast.Id>(id = id, isNew = isNew) {

        data class Id(
            private val value: UUID,
        ) : ValueObject {
            fun toUUIDValue(): UUID = value
        }

        data class StreamingTime(
            private val value: Instant,
        ) : ValueObject {
            fun toInstantValue(): Instant = value
        }

        companion object {
            fun addBroadcast(
                broadcastIdGenerator: BroadcastIdGenerator,
                streamedAt: StreamingTime,
            ): Broadcast {
                val id = broadcastIdGenerator.generate()

                return Broadcast(
                    id = id,
                    isNew = true,
                    streamedAt = streamedAt,
                )
            }

            fun restoreBroadcast(
                id: Id,
                streamedAt: StreamingTime,
            ): Broadcast {
                return Broadcast(
                    id = id,
                    isNew = false,
                    streamedAt = streamedAt,
                )
            }
        }

        override fun markPersistedCascade() {
            isNew = false
        }
    }

    class Sequence internal constructor(
        id: Id,
        isNew: Boolean,
        val order: Order,
    ) : DomainEntity<Sequence.Id>(id = id, isNew = isNew) {

        data class Id(
            private val value: UUID,
        ) : ValueObject {
            fun toUUIDValue(): UUID = value
        }

        data class Order(
            private val value: Long,
        ) : ValueObject {
            fun toLongValue(): Long = value
        }

        companion object {

            fun addSequence(
                sequenceIdGenerator: SequenceIdGenerator,
                order: Order,
            ): Sequence {
                val id = sequenceIdGenerator.generate()

                return Sequence(
                    id = id,
                    isNew = true,
                    order = order,
                )
            }

            fun restoreSequence(
                id: Id,
                order: Order,
            ): Sequence {
                return Sequence(
                    id = id,
                    isNew = false,
                    order = order,
                )
            }
        }

        override fun markPersistedCascade() {
            isNew = false
        }
    }

    companion object {
        fun addAudio(
            audioIdGenerator: AudioIdGenerator,
            audioAlreadyExists: AudioAlreadyExists,
            name: Name,
            description: Description,
            file: File,
            createdBy: UserId,
        ): Audio {
            if (audioAlreadyExists(name)) {
                throw DomainException.AudioAlreadyExistsException(
                    "Audio with name '${name.toStringValue()}' already exists!"
                )
            }

            val id = audioIdGenerator.generate()

            return Audio(
                id = id,
                isNew = true,
                name = name,
                description = description,
                file = file,
                createdBy = createdBy,
                removedBy = null,
                broadcasts = emptyList(),
                sequences = emptyList(),
            )
        }

        fun restoreAudio(
            id: AudioId,
            name: Name,
            description: Description,
            file: File,
            createdBy: UserId,
            removedBy: UserId?,
            broadcasts: List<Broadcast>,
            sequences: List<Sequence>,
        ): Audio {
            return Audio(
                id = id,
                isNew = false,
                name = name,
                description = description,
                file = file,
                createdBy = createdBy,
                removedBy = removedBy,
                broadcasts = broadcasts,
                sequences = sequences,
            )
        }
    }

    override fun markPersistedCascade() {
        broadcasts.forEach { broadcast ->
            broadcast.markPersistedCascade()
        }
        sequences.forEach { sequence ->
            sequence.markPersistedCascade()
        }
        isNew = false
    }
}
