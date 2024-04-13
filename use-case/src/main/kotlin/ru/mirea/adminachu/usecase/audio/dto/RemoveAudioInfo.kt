package ru.mirea.adminachu.usecase.audio.dto

import ru.mirea.adminachu.domain.common.definition.AudioId
import ru.mirea.adminachu.domain.common.definition.UserId

data class RemoveAudioInfo(
    val audioId: AudioId,
    val userId: UserId,
)
