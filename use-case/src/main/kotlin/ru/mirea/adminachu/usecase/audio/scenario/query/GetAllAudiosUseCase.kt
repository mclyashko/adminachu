package ru.mirea.adminachu.usecase.audio.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioExtractor
import ru.mirea.adminachu.usecase.audio.declaration.query.GetAllAudios
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo
import ru.mirea.adminachu.usecase.audio.dto.extension.toAudioInfoDto

@Component
class GetAllAudiosUseCase(
    private val audioExtractor: AudioExtractor
) : GetAllAudios {
    override fun execute(): List<AudioInfo> {
        return audioExtractor
            .getAll()
            .map { audio ->
                audio.toAudioInfoDto()
            }
    }
}
