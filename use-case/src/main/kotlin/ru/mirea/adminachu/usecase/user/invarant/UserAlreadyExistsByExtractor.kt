package ru.mirea.adminachu.usecase.user.invarant

import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.common.util.metric.MetricLogger
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.domain.user.declaration.UserAlreadyExists
import ru.mirea.adminachu.usecase.user.declaration.acess.UserExtractor

@Component
class UserAlreadyExistsByExtractor(
    private val userExtractor: UserExtractor,
    private val metricsLogger: MetricLogger,
) : UserAlreadyExists {
    override fun invoke(userName: User.Name, email: User.Email): Boolean {
        val userByUserName = userExtractor.getByUserName(userName)
        val userByEmail = userExtractor.getByEmail(email)

        return if (listOf(userByUserName, userByEmail).any { user -> user != null }) {
            metricsLogger.registerCounter(
                name = MetricLogger.USER_ALREADY_EXISTS_VALIDATION_FAILED
            )
            true
        } else {
            false
        }
    }
}
