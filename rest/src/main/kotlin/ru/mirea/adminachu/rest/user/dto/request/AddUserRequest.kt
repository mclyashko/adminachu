package ru.mirea.adminachu.rest.user.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for adding a new user.")
data class AddUserRequest(
    @field:Schema(
        description = "User name",
        example = "admin",
        type = "string",
    )
    val userName: String,
    @field:Schema(
        description = "User email",
        example = "admin@admin.admin",
        type = "string",
    )
    val email: String,
    @field:Schema(
        description = "User password",
        example = "admin",
        type = "string",
    )
    val password: String,
)
