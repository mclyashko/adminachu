package ru.mirea.adminachu.db.audio.repository

import org.springframework.data.repository.CrudRepository
import ru.mirea.adminachu.db.audio.entity.BroadcastEntity
import java.util.UUID

interface BroadcastRepository : CrudRepository<BroadcastEntity, UUID> {
    fun findAllByAudioId(audioId: UUID): List<BroadcastEntity>
}
