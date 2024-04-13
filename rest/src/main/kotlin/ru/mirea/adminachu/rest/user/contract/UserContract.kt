package ru.mirea.adminachu.rest.user.contract

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import ru.mirea.adminachu.rest.user.dto.request.AddUserRequest
import ru.mirea.adminachu.rest.user.dto.request.UpdateUserRequest
import ru.mirea.adminachu.rest.user.dto.response.UserResponse
import java.util.UUID

@OpenAPIDefinition(
    info = Info(
        title = "adminachu",
        version = "1.0.0"
    )
)
interface UserContract {
    @Operation(
        summary = "Get all users"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getAllUsers(): ResponseEntity<List<UserResponse>>

    @Operation(
        summary = "Get user by email"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "User not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getUserByEmail(email: String): ResponseEntity<UserResponse>

    @Operation(
        summary = "Get user by user name"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "User not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getUserByUserName(userName: String): ResponseEntity<UserResponse>

    @Operation(
        summary = "Add new user"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun addNewUser(addUserRequest: AddUserRequest): ResponseEntity<UserResponse>

    @Operation(
        summary = "Update information about the user by id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "User not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun updateUser(
        id: UUID,
        updateUserRequest: UpdateUserRequest,
    ): ResponseEntity<UserResponse>
}
