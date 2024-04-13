package ru.mirea.adminachu.usecase.audio.declaration.acess

import ru.mirea.adminachu.domain.audio.Audio

interface AudioPersister {
    fun save(audio: Audio)
}
