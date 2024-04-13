package ru.mirea.adminachu.usecase.user.scenario.command

import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.domain.user.command.markAsDisabled
import ru.mirea.adminachu.usecase.user.declaration.acess.UserExtractor
import ru.mirea.adminachu.usecase.user.declaration.acess.UserPersister
import ru.mirea.adminachu.usecase.user.declaration.command.DisableUserById
import ru.mirea.adminachu.usecase.user.dto.DisableUserInfo
import ru.mirea.adminachu.usecase.user.dto.UserInfo
import ru.mirea.adminachu.usecase.user.dto.extension.toUserInfoDto

@Component
class DisableUserByIdUseCase(
    private val userExtractor: UserExtractor,
    private val userPersister: UserPersister,
) : DisableUserById {
    override fun execute(disableUserInfo: DisableUserInfo): UserInfo {
        val user = userExtractor
            .getById(id = disableUserInfo.userId)
            ?: throw DomainException.DisableUserByIdNotFoundException(
                "User with id '${disableUserInfo.userId.toUUIDValue()}' doesn't exist!"
            )

        val disabledUser = user.markAsDisabled()

        return disabledUser.let {
            userPersister.save(it)
            it.toUserInfoDto()
        }
    }
}
