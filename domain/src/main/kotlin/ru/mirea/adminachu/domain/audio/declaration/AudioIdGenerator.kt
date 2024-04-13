package ru.mirea.adminachu.domain.audio.declaration

import ru.mirea.adminachu.domain.common.definition.AudioId

interface AudioIdGenerator {
    fun generate(): AudioId
}
