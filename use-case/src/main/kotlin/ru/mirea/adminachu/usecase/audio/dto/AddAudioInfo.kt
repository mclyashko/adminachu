package ru.mirea.adminachu.usecase.audio.dto

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.UserId

data class AddAudioInfo(
    val name: Audio.Name,
    val description: Audio.Description,
    val file: Audio.File,
    val createdBy: UserId,
)
