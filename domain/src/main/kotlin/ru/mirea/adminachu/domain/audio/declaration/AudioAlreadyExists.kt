package ru.mirea.adminachu.domain.audio.declaration

import ru.mirea.adminachu.domain.audio.Audio

fun interface AudioAlreadyExists {
    operator fun invoke(name: Audio.Name): Boolean
}
