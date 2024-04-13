package ru.mirea.adminachu.rest.user.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.rest.user.contract.UserContract
import ru.mirea.adminachu.rest.user.dto.request.AddUserRequest
import ru.mirea.adminachu.rest.user.dto.request.UpdateUserRequest
import ru.mirea.adminachu.rest.user.dto.request.extension.toAddUserInfo
import ru.mirea.adminachu.rest.user.dto.request.extension.toUpdateUserInfo
import ru.mirea.adminachu.rest.user.dto.response.UserResponse
import ru.mirea.adminachu.rest.user.dto.response.extension.toResponseDto
import ru.mirea.adminachu.usecase.user.declaration.query.GetAllUsers
import ru.mirea.adminachu.usecase.user.declaration.query.GetUserByEmail
import ru.mirea.adminachu.usecase.user.declaration.query.GetUserByUserName
import ru.mirea.adminachu.usecase.user.scenario.command.AddNewUserUseCase
import ru.mirea.adminachu.usecase.user.scenario.command.UpdateUserByIdUseCase
import java.util.UUID

@RestController
@RequestMapping("/api")
@Suppress("LongParameterList")
class UserController(
    private val passwordEncoder: PasswordEncoder,
    private val getAllUsers: GetAllUsers,
    private val getUserByEmail: GetUserByEmail,
    private val getUserByUserName: GetUserByUserName,
    private val addNewUserUseCase: AddNewUserUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase,
) : UserContract {

    @GetMapping("/user")
    override fun getAllUsers(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(
            getAllUsers
                .execute()
                .map { userInfo ->
                    userInfo.toResponseDto()
                }
        )
    }

    @GetMapping("/user/email")
    override fun getUserByEmail(
        @RequestParam email: String,
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(
            getUserByEmail
                .execute(email = User.Email(email))
                .toResponseDto()
        )
    }

    @GetMapping("/user/userName")
    override fun getUserByUserName(
        @RequestParam userName: String,
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(
            getUserByUserName
                .execute(userName = User.Name(userName))
                .toResponseDto()
        )
    }

    @PostMapping("/user")
    override fun addNewUser(
        @RequestBody addUserRequest: AddUserRequest,
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(
            addNewUserUseCase
                .execute(
                    addUserInfo = addUserRequest
                        .toAddUserInfo(passwordEncoder = passwordEncoder)
                )
                .toResponseDto()
        )
    }

    @PutMapping("/user/id")
    override fun updateUser(
        @RequestParam id: UUID,
        @RequestBody updateUserRequest: UpdateUserRequest
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(
            updateUserByIdUseCase
                .execute(
                    updateUserInfo = updateUserRequest.toUpdateUserInfo(
                        passwordEncoder = passwordEncoder,
                        userId = id,
                    )
                )
                .toResponseDto()
        )
    }
}
