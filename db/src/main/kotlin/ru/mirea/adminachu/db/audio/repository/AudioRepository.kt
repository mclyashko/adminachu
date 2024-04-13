package ru.mirea.adminachu.db.audio.repository

import org.springframework.data.repository.CrudRepository
import ru.mirea.adminachu.db.audio.entity.AudioEntity
import java.util.Optional
import java.util.UUID

interface AudioRepository : CrudRepository<AudioEntity, UUID> {
    fun findByName(name: String): Optional<AudioEntity>
}
