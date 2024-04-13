package ru.mirea.adminachu.domain.common.design.entity

abstract class DomainEntity<T> protected constructor(
    val id: T,
    internal var isNew: Boolean,
) : ValueObject {
    fun isNew(): Boolean = isNew
    abstract fun markPersistedCascade()
}
