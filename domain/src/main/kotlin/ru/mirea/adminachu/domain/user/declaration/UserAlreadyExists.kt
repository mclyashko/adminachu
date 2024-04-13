package ru.mirea.adminachu.domain.user.declaration

import ru.mirea.adminachu.domain.user.User

interface UserAlreadyExists {
    operator fun invoke(userName: User.Name, email: User.Email): Boolean
}
