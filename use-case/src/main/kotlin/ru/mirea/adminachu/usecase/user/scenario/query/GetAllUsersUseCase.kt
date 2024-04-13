package ru.mirea.adminachu.usecase.user.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.adminachu.usecase.user.declaration.acess.UserExtractor
import ru.mirea.adminachu.usecase.user.declaration.query.GetAllUsers
import ru.mirea.adminachu.usecase.user.dto.UserInfo
import ru.mirea.adminachu.usecase.user.dto.extension.toUserInfoDto

@Component
class GetAllUsersUseCase(
    private val userExtractor: UserExtractor,
) : GetAllUsers {
    override fun execute(): List<UserInfo> {
        return userExtractor
            .getAll()
            .map { user ->
                user.toUserInfoDto()
            }
    }
}
