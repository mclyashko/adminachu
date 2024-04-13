package ru.mirea.adminachu.db.user.acess

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.mirea.adminachu.db.user.entity.extension.toUserEntity
import ru.mirea.adminachu.db.user.repository.UserRepository
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.declaration.acess.UserPersister

@Repository
class UserPersisterImpl(
    private val userRepository: UserRepository,
) : UserPersister {

    @Transactional
    override fun save(user: User) {
        (user to user.toUserEntity())
            .let { (user, userEntity) ->
                if (user.isNew()) {
                    userEntity.apply { isNew = true }
                }
                userRepository.save(userEntity)
            }
    }
}
