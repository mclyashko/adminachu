package ru.mirea.adminachu.usecase.audio.dto.extension

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo

internal fun Audio.toAudioInfoDto(): AudioInfo = AudioInfo(
    id = id,
    name = name,
    description = description,
    file = file,
    createdBy = createdBy,
    removedBy = removedBy,
    broadcasts = broadcasts,
    sequences = sequences,
)
