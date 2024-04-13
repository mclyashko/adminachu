package ru.mirea.adminachu.usecase.audio.declaration.command

import ru.mirea.adminachu.usecase.audio.dto.AudioInfo
import ru.mirea.adminachu.usecase.audio.dto.RemoveAudioInfo

interface RemoveAudioById {
    fun execute(removeAudioInfo: RemoveAudioInfo): AudioInfo
}
