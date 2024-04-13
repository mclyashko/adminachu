package ru.mirea.adminachu.usecase.audio.declaration.acess

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.AudioId

interface AudioExtractor {
    fun getById(id: AudioId): Audio?

    fun getByName(name: Audio.Name): Audio?

    fun getAll(): List<Audio>
}
