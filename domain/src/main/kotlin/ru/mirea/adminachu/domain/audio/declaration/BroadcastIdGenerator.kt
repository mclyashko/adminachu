package ru.mirea.adminachu.domain.audio.declaration

import ru.mirea.adminachu.domain.audio.Audio

interface BroadcastIdGenerator {
    fun generate(): Audio.Broadcast.Id
}
