package ru.mirea.adminachu.usecase.audio.declaration.command

import ru.mirea.adminachu.usecase.audio.dto.AudioInfo
import ru.mirea.adminachu.usecase.audio.dto.UpdateAudioInfo

interface UpdateAudioById {
    fun execute(updateAudioInfo: UpdateAudioInfo): AudioInfo
}
