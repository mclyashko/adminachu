package ru.mirea.adminachu.usecase.audio.declaration.query

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo

interface GetAudioByName {
    fun execute(name: Audio.Name): AudioInfo
}
