package ru.mirea.adminachu.usecase.audio.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.adminachu.domain.audio.command.markAsRemovedBy
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioExtractor
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioPersister
import ru.mirea.adminachu.usecase.audio.declaration.command.RemoveAudioById
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo
import ru.mirea.adminachu.usecase.audio.dto.RemoveAudioInfo
import ru.mirea.adminachu.usecase.audio.dto.extension.toAudioInfoDto

@Component
class RemoveAudioByIdUseCase(
    private val audioExtractor: AudioExtractor,
    private val audioPersister: AudioPersister,
) : RemoveAudioById {
    @Transactional
    override fun execute(removeAudioInfo: RemoveAudioInfo): AudioInfo {
        val audio = audioExtractor
            .getById(id = removeAudioInfo.audioId)
            ?: throw DomainException.RemoveAudioByIdNotFoundException(
                "Audio with id '${removeAudioInfo.audioId.toUUIDValue()}' doesn't exist!"
            )

        val removedAudio = audio.markAsRemovedBy(removedBy = removeAudioInfo.userId)

        return removedAudio.let {
            audioPersister.save(it)
            it.toAudioInfoDto()
        }
    }
}
