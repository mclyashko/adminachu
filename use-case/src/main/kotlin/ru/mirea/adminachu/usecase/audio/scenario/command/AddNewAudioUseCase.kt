package ru.mirea.adminachu.usecase.audio.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.audio.declaration.AudioAlreadyExists
import ru.mirea.adminachu.domain.audio.declaration.AudioIdGenerator
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioPersister
import ru.mirea.adminachu.usecase.audio.declaration.command.AddNewAudio
import ru.mirea.adminachu.usecase.audio.dto.AddAudioInfo
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo
import ru.mirea.adminachu.usecase.audio.dto.extension.toAudioInfoDto

@Component
class AddNewAudioUseCase(
    private val audioIdGenerator: AudioIdGenerator,
    private val audioAlreadyExists: AudioAlreadyExists,
    private val audioPersister: AudioPersister,
) : AddNewAudio {
    @Transactional
    override fun execute(addAudioInfo: AddAudioInfo): AudioInfo {
        return Audio.addAudio(
            audioIdGenerator = audioIdGenerator,
            audioAlreadyExists = audioAlreadyExists,
            name = addAudioInfo.name,
            description = addAudioInfo.description,
            file = addAudioInfo.file,
            createdBy = addAudioInfo.createdBy,
        ).let {
            audioPersister.save(it)
            it.toAudioInfoDto()
        }
    }
}
