package ru.mirea.adminachu.usecase.user.scenario.command

import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.domain.user.command.markAsEnabled
import ru.mirea.adminachu.usecase.user.declaration.acess.UserExtractor
import ru.mirea.adminachu.usecase.user.declaration.acess.UserPersister
import ru.mirea.adminachu.usecase.user.declaration.command.EnableUserById
import ru.mirea.adminachu.usecase.user.dto.EnableUserInfo
import ru.mirea.adminachu.usecase.user.dto.UserInfo
import ru.mirea.adminachu.usecase.user.dto.extension.toUserInfoDto

@Component
class EnableUserByIdUseCase(
    private val userExtractor: UserExtractor,
    private val userPersister: UserPersister,
) : EnableUserById {
    override fun execute(enableUserInfo: EnableUserInfo): UserInfo {
        val user = userExtractor
            .getById(id = enableUserInfo.userId)
            ?: throw DomainException.EnableUserByIdNotFoundException(
                "User with id '${enableUserInfo.userId.toUUIDValue()}' doesn't exist!"
            )

        val enabledUser = user.markAsEnabled()

        return enabledUser.let {
            userPersister.save(it)
            it.toUserInfoDto()
        }
    }
}
