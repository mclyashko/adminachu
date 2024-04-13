package ru.mirea.adminachu.usecase.audio.declaration.query

import ru.mirea.adminachu.usecase.audio.dto.AudioInfo

interface GetAllAudios {
    fun execute(): List<AudioInfo>
}
