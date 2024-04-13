package ru.mirea.adminachu.domain.audio.command

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.UserId

fun Audio.markAsRemovedBy(removedBy: UserId) = Audio.restoreAudio(
    id = id,
    name = name,
    description = description,
    file = file,
    createdBy = createdBy,
    removedBy = removedBy,
    broadcasts = broadcasts,
    sequences = sequences,
)
