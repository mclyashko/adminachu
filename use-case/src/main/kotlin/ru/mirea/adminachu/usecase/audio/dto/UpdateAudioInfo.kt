package ru.mirea.adminachu.usecase.audio.dto

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.AudioId

data class UpdateAudioInfo(
    val audioId: AudioId,
    val name: Audio.Name,
    val description: Audio.Description,
    val file: Audio.File,
)
