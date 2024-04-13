package ru.mirea.adminachu.usecase.audio.declaration.command

import ru.mirea.adminachu.usecase.audio.dto.AddAudioInfo
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo

interface AddNewAudio {
    fun execute(addAudioInfo: AddAudioInfo): AudioInfo
}
