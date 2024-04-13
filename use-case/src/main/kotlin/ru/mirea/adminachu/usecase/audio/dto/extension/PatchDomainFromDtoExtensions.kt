package ru.mirea.adminachu.usecase.audio.dto.extension

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.usecase.audio.dto.UpdateAudioInfo

fun Audio.updateFromDto(updateAudioInfo: UpdateAudioInfo): Audio = Audio.restoreAudio(
    id = id,
    name = updateAudioInfo.name,
    description = updateAudioInfo.description,
    file = updateAudioInfo.file,
    createdBy = createdBy,
    removedBy = removedBy,
    broadcasts = broadcasts,
    sequences = sequences,
)
