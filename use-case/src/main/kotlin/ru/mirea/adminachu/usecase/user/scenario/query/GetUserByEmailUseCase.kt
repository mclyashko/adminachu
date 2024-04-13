package ru.mirea.adminachu.usecase.user.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.common.design.exception.DomainException
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.usecase.user.declaration.acess.UserExtractor
import ru.mirea.adminachu.usecase.user.declaration.query.GetUserByEmail
import ru.mirea.adminachu.usecase.user.dto.UserInfo
import ru.mirea.adminachu.usecase.user.dto.extension.toUserInfoDto

@Component
class GetUserByEmailUseCase(
    private val userExtractor: UserExtractor,
) : GetUserByEmail {
    override fun execute(email: User.Email): UserInfo {
        return userExtractor
            .getByEmail(email = email)
            ?.toUserInfoDto()
            ?: throw DomainException.GetUserByEmailNotFoundException(
                "User with email '${email.toStringValue()}' doesn't exist!"
            )
    }
}
