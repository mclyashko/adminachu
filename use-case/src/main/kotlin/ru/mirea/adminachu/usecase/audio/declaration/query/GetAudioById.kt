package ru.mirea.adminachu.usecase.audio.declaration.query

import ru.mirea.adminachu.domain.common.definition.AudioId
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo

interface GetAudioById {
    fun execute(id: AudioId): AudioInfo
}
