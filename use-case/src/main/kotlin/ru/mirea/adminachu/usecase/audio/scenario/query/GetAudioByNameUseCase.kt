package ru.mirea.adminachu.usecase.audio.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioExtractor
import ru.mirea.adminachu.usecase.audio.declaration.query.GetAudioByName
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo
import ru.mirea.adminachu.usecase.audio.dto.extension.toAudioInfoDto

@Component
class GetAudioByNameUseCase(
    private val audioExtractor: AudioExtractor,
) : GetAudioByName {
    override fun execute(name: Audio.Name): AudioInfo {
        return audioExtractor
            .getByName(name = name)
            ?.toAudioInfoDto()
            ?: throw DomainException.GetAudioByNameNotFoundException(
                "Audio with name '${name.toStringValue()}' doesn't exist!"
            )
    }
}
