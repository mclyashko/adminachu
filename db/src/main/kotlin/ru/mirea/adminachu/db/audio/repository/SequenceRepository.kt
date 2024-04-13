package ru.mirea.adminachu.db.audio.repository

import org.springframework.data.repository.CrudRepository
import ru.mirea.adminachu.db.audio.entity.SequenceEntity
import java.util.UUID

interface SequenceRepository : CrudRepository<SequenceEntity, UUID> {
    fun findAllByAudioId(audioId: UUID): List<SequenceEntity>
}
