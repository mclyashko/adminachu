package ru.mirea.adminachu.rest.user.dto.request.extension

import org.springframework.security.crypto.password.PasswordEncoder
import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.rest.user.dto.request.AddUserRequest
import ru.mirea.adminachu.rest.user.dto.request.UpdateUserRequest
import ru.mirea.adminachu.usecase.user.dto.AddUserInfo
import ru.mirea.adminachu.usecase.user.dto.UpdateUserInfo
import java.util.UUID

fun AddUserRequest.toAddUserInfo(passwordEncoder: PasswordEncoder): AddUserInfo = AddUserInfo(
    userName = User.Name(userName),
    email = User.Email(email),
    password = User.Password(passwordEncoder.encode(password)),
)

fun UpdateUserRequest.toUpdateUserInfo(passwordEncoder: PasswordEncoder, userId: UUID): UpdateUserInfo = UpdateUserInfo(
    userId = UserId(userId),
    userName = User.Name(userName),
    email = User.Email(email),
    password = User.Password(passwordEncoder.encode(password)),
    activated = User.ActivationStatus(activated),
)
