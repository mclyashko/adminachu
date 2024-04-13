package ru.mirea.adminachu.db.audio.acess

import org.springframework.stereotype.Repository
import ru.mirea.adminachu.db.audio.entity.AudioEntity
import ru.mirea.adminachu.db.audio.repository.AudioRepository
import ru.mirea.adminachu.db.audio.repository.BroadcastRepository
import ru.mirea.adminachu.db.audio.repository.SequenceRepository
import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.AudioId
import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioExtractor
import kotlin.jvm.optionals.getOrNull

@Repository
class AudioExtractorImpl(
    private val audioRepository: AudioRepository,
    private val broadcastRepository: BroadcastRepository,
    private val sequenceRepository: SequenceRepository,
) : AudioExtractor {
    override fun getById(id: AudioId): Audio? {
        val audioEntity = audioRepository
            .findById(id.toUUIDValue())
            .getOrNull()

        return audioEntity?.let {
            constructDomain(it)
        }
    }

    override fun getByName(name: Audio.Name): Audio? {
        val audioEntity = audioRepository
            .findByName(name.toStringValue())
            .getOrNull()

        return audioEntity?.let {
            constructDomain(it)
        }
    }

    override fun getAll(): List<Audio> {
        val audioEntities = audioRepository
            .findAll()

        return audioEntities.map {
            constructDomain(it)
        }
    }

    private fun constructDomain(audioEntity: AudioEntity): Audio {
        val broadcastEntities = broadcastRepository.findAllByAudioId(audioEntity.id)
        val sequenceEntities = sequenceRepository.findAllByAudioId(audioEntity.id)

        return Audio.restoreAudio(
            id = AudioId(audioEntity.id),
            name = Audio.Name(audioEntity.name),
            description = Audio.Description(audioEntity.description),
            file = Audio.File(audioEntity.file),
            createdBy = UserId(audioEntity.createdBy),
            removedBy = audioEntity.removedBy?.let {
                UserId(it)
            },
            broadcasts = broadcastEntities.map { broadcastEntity ->
                Audio.Broadcast.restoreBroadcast(
                    id = Audio.Broadcast.Id(broadcastEntity.id),
                    streamedAt = Audio.Broadcast.StreamingTime(broadcastEntity.streamedAt),
                )
            },
            sequences = sequenceEntities.map { sequenceEntity ->
                Audio.Sequence.restoreSequence(
                    id = Audio.Sequence.Id(sequenceEntity.id),
                    order = Audio.Sequence.Order(sequenceEntity.order),
                )
            }
        )
    }
}
