package ru.mirea.adminachu.usecase.audio.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioExtractor
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioPersister
import ru.mirea.adminachu.usecase.audio.declaration.command.UpdateAudioById
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo
import ru.mirea.adminachu.usecase.audio.dto.UpdateAudioInfo
import ru.mirea.adminachu.usecase.audio.dto.extension.toAudioInfoDto
import ru.mirea.adminachu.usecase.audio.dto.extension.updateFromDto

@Component
class UpdateAudioByIdUseCase(
    private val audioExtractor: AudioExtractor,
    private val audioPersister: AudioPersister,
) : UpdateAudioById {
    @Transactional
    override fun execute(updateAudioInfo: UpdateAudioInfo): AudioInfo {
        val audio = audioExtractor
            .getById(id = updateAudioInfo.audioId)
            ?: throw DomainException.UpdateAudioByIdNotFoundException(
                "Audio with id '${updateAudioInfo.audioId.toUUIDValue()}' doesn't exist!"
            )

        val updatedAudio = audio.updateFromDto(updateAudioInfo = updateAudioInfo)

        return updatedAudio.let {
            audioPersister.save(it)
            it.toAudioInfoDto()
        }
    }
}
