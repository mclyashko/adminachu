package ru.mirea.adminachu.usecase.audio.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.common.definition.AudioId
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioExtractor
import ru.mirea.adminachu.usecase.audio.declaration.query.GetAudioById
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo
import ru.mirea.adminachu.usecase.audio.dto.extension.toAudioInfoDto

@Component
class GetAudioByIdUseCase(
    private val audioExtractor: AudioExtractor,
) : GetAudioById {
    override fun execute(id: AudioId): AudioInfo {
        return audioExtractor
            .getById(id = id)
            ?.toAudioInfoDto()
            ?: throw DomainException.GetAudioByIdNotFoundException(
                "Audio with id '${id.toUUIDValue()}' doesn't exist!"
            )
    }
}
