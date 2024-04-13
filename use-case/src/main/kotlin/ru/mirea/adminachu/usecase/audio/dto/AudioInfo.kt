package ru.mirea.adminachu.usecase.audio.dto

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.AudioId
import ru.mirea.adminachu.domain.common.definition.UserId

data class AudioInfo(
    val id: AudioId,
    val name: Audio.Name,
    val description: Audio.Description,
    val file: Audio.File,
    val createdBy: UserId,
    val removedBy: UserId?,
    val broadcasts: List<Audio.Broadcast>,
    val sequences: List<Audio.Sequence>,
)
