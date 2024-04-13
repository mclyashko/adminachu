package ru.mirea.adminachu.domain.user

import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.domain.common.design.entity.AggregateRoot
import ru.mirea.adminachu.domain.common.design.entity.ValueObject
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.domain.user.declaration.UserAlreadyExists
import ru.mirea.adminachu.domain.user.declaration.UserIdGenerator

class User internal constructor(
    id: UserId,
    isNew: Boolean,
    val userName: Name,
    val email: Email,
    val password: Password,
    val activated: ActivationStatus,
) : AggregateRoot<UserId>(id = id, isNew = isNew) {

    data class Name(
        private val value: String,
    ) : ValueObject {
        fun toStringValue(): String = value
    }

    data class Email(
        private val value: String,
    ) : ValueObject {
        fun toStringValue(): String = value
    }

    data class Password(
        private val value: String,
    ) : ValueObject {
        fun toStringValue(): String = value
    }

    data class ActivationStatus(
        private val value: Boolean,
    ) : ValueObject {
        fun toBooleanValue(): Boolean = value
    }

    companion object {
        fun addUser(
            userIdGenerator: UserIdGenerator,
            userAlreadyExists: UserAlreadyExists,
            userName: Name,
            email: Email,
            password: Password,
            activated: ActivationStatus,
        ): User {
            if (userAlreadyExists(userName = userName, email = email)) {
                throw DomainException.UserAlreadyExistsException(
                    "User with username '${userName.toStringValue()}' or " +
                        "email '${email.toStringValue()}' already exists!"
                )
            }

            val id = userIdGenerator.generate()

            return User(
                id = id,
                isNew = true,
                userName = userName,
                email = email,
                password = password,
                activated = activated,
            )
        }

        fun restoreUser(
            id: UserId,
            userName: Name,
            email: Email,
            password: Password,
            activated: ActivationStatus,
        ): User {
            return User(
                id = id,
                isNew = false,
                userName = userName,
                email = email,
                password = password,
                activated = activated,
            )
        }
    }

    override fun markPersistedCascade() {
        isNew = false
    }
}
