package ru.mirea.adminachu.db.audio.acess

import org.springframework.stereotype.Repository
import ru.mirea.adminachu.domain.audio.declaration.AudioIdGenerator
import ru.mirea.adminachu.domain.common.definition.AudioId
import java.util.UUID

@Repository
class AudioIdGeneratorImpl : AudioIdGenerator {
    override fun generate(): AudioId {
        val id = UUID.randomUUID()
        return AudioId(id)
    }
}
