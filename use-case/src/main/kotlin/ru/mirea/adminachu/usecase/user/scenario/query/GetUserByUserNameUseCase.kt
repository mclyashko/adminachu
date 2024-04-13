package ru.mirea.adminachu.usecase.user.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.declaration.acess.UserExtractor
import ru.mirea.adminachu.usecase.user.declaration.query.GetUserByUserName
import ru.mirea.adminachu.usecase.user.dto.UserInfo
import ru.mirea.adminachu.usecase.user.dto.extension.toUserInfoDto

@Component
class GetUserByUserNameUseCase(
    private val userExtractor: UserExtractor,
) : GetUserByUserName {
    override fun execute(userName: User.Name): UserInfo {
        return userExtractor
            .getByUserName(userName = userName)
            ?.toUserInfoDto()
            ?: throw DomainException.GetUserByUserNameNotFoundException(
                "User with user name '${userName.toStringValue()}' doesn't exist!"
            )
    }
}
