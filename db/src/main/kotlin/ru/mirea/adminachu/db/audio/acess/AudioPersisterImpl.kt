package ru.mirea.adminachu.db.audio.acess

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.mirea.adminachu.db.audio.entity.extension.toAudioEntity
import ru.mirea.adminachu.db.audio.entity.extension.toBroadcastEntity
import ru.mirea.adminachu.db.audio.entity.extension.toSequenceEntity
import ru.mirea.adminachu.db.audio.repository.AudioRepository
import ru.mirea.adminachu.db.audio.repository.BroadcastRepository
import ru.mirea.adminachu.db.audio.repository.SequenceRepository
import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioPersister

@Repository
class AudioPersisterImpl(
    private val audioRepository: AudioRepository,
    private val broadcastRepository: BroadcastRepository,
    private val sequenceRepository: SequenceRepository,
) : AudioPersister {

    @Transactional
    override fun save(audio: Audio) {
        (audio to audio.toAudioEntity())
            .let { (audio, audioEntity) ->
                if (audio.isNew()) {
                    audioEntity.apply { isNew = true }
                }
                audioRepository.save(audioEntity)
            }

        audio.broadcasts.forEach { broadcast ->
            val broadcastEntity = broadcast.toBroadcastEntity(audioId = audio.id)
            if (broadcast.isNew()) {
                broadcastEntity.apply { isNew = true }
            }
            broadcastRepository.save(broadcastEntity)
        }

        audio.sequences.forEach { sequence ->
            val sequenceEntity = sequence.toSequenceEntity(audioId = audio.id)
            if (sequence.isNew()) {
                sequenceEntity.apply { isNew = true }
            }
            sequenceRepository.save(sequenceEntity)
        }

        audio.markPersistedCascade()
    }
}
