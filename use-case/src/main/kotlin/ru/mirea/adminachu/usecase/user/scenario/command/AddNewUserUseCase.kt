package ru.mirea.adminachu.usecase.user.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.domain.user.declaration.UserAlreadyExists
import ru.mirea.adminachu.domain.user.declaration.UserIdGenerator
import ru.mirea.adminachu.usecase.user.declaration.acess.UserPersister
import ru.mirea.adminachu.usecase.user.declaration.command.AddNewUser
import ru.mirea.adminachu.usecase.user.dto.AddUserInfo
import ru.mirea.adminachu.usecase.user.dto.UserInfo
import ru.mirea.adminachu.usecase.user.dto.extension.toUserInfoDto

@Component
class AddNewUserUseCase(
    private val userIdGenerator: UserIdGenerator,
    private val userAlreadyExists: UserAlreadyExists,
    private val userPersister: UserPersister,
) : AddNewUser {
    @Transactional
    override fun execute(addUserInfo: AddUserInfo): UserInfo {
        return User.addUser(
            userIdGenerator = userIdGenerator,
            userAlreadyExists = userAlreadyExists,
            userName = addUserInfo.userName,
            email = addUserInfo.email,
            password = addUserInfo.password,
            activated = User.ActivationStatus(value = false),
        ).let {
            userPersister.save(it)
            it.toUserInfoDto()
        }
    }
}
