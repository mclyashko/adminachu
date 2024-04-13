package ru.mirea.adminachu.domain.common.design.entity

abstract class AggregateRoot<T> protected constructor(
    id: T,
    isNew: Boolean
) : DomainEntity<T>(id = id, isNew = isNew)
