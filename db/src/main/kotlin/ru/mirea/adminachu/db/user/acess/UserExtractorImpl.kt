package ru.mirea.adminachu.db.user.acess

import org.springframework.stereotype.Repository
import ru.mirea.adminachu.db.user.entity.UserEntity
import ru.mirea.adminachu.db.user.repository.UserRepository
import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.declaration.acess.UserExtractor
import kotlin.jvm.optionals.getOrNull

@Repository
class UserExtractorImpl(
    private val userRepository: UserRepository,
) : UserExtractor {

    override fun getById(id: UserId): User? {
        val userEntity = userRepository
            .findById(id.toUUIDValue())
            .getOrNull()

        return userEntity?.let {
            constructDomain(it)
        }
    }

    override fun getByUserName(userName: User.Name): User? {
        val userEntity = userRepository
            .findByUserName(userName.toStringValue())
            .getOrNull()

        return userEntity?.let {
            constructDomain(it)
        }
    }

    override fun getByEmail(email: User.Email): User? {
        val userEntity = userRepository
            .findByEmail(email.toStringValue())
            .getOrNull()

        return userEntity?.let {
            constructDomain(it)
        }
    }

    override fun getAll(): List<User> {
        val userEntities = userRepository
            .findAll()

        return userEntities.map {
            constructDomain(it)
        }
    }

    private fun constructDomain(userEntity: UserEntity): User {
        return User.restoreUser(
            id = UserId(userEntity.id),
            userName = User.Name(userEntity.userName),
            email = User.Email(userEntity.email),
            password = User.Password(userEntity.password),
            activated = User.ActivationStatus(userEntity.activated),
        )
    }
}
