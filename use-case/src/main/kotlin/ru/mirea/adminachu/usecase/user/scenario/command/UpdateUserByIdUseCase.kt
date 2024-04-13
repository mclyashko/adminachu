package ru.mirea.adminachu.usecase.user.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.usecase.user.declaration.acess.UserExtractor
import ru.mirea.adminachu.usecase.user.declaration.acess.UserPersister
import ru.mirea.adminachu.usecase.user.declaration.command.UpdateUserById
import ru.mirea.adminachu.usecase.user.dto.UpdateUserInfo
import ru.mirea.adminachu.usecase.user.dto.UserInfo
import ru.mirea.adminachu.usecase.user.dto.extension.toUserInfoDto
import ru.mirea.adminachu.usecase.user.dto.extension.updateFromDto

@Component
class UpdateUserByIdUseCase(
    private val userExtractor: UserExtractor,
    private val userPersister: UserPersister,
) : UpdateUserById {
    @Transactional
    override fun execute(updateUserInfo: UpdateUserInfo): UserInfo {
        val user = userExtractor
            .getById(updateUserInfo.userId)
            ?: throw DomainException.UpdateUserByIdNotFoundException(
                "User with id '${updateUserInfo.userId.toUUIDValue()}' doesn't exist!"
            )

        val updatedUser = user.updateFromDto(updateUserInfo = updateUserInfo)

        return updatedUser.let {
            userPersister.save(it)
            it.toUserInfoDto()
        }
    }
}
