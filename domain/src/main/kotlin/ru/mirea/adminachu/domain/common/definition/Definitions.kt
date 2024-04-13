package ru.mirea.adminachu.domain.common.definition

import ru.mirea.adminachu.domain.common.design.entity.ValueObject
import java.util.UUID

data class AudioId(
    private val value: UUID,
) : ValueObject {
    fun toUUIDValue(): UUID = value
}

data class UserId(
    private val value: UUID,
) : ValueObject {
    fun toUUIDValue(): UUID = value
}
