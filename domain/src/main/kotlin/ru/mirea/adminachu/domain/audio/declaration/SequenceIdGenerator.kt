package ru.mirea.adminachu.domain.audio.declaration

import ru.mirea.adminachu.domain.audio.Audio

interface SequenceIdGenerator {
    fun generate(): Audio.Sequence.Id
}
