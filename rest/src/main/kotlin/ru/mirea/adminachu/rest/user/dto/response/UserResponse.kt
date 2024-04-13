package ru.mirea.adminachu.rest.user.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(description = "Model for the user.")
data class UserResponse(
    @field:Schema(
        description = "User ID",
        example = "3",
        type = "uuid",
    )
    val id: UUID,
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
        description = "Encoded user password",
        example = "admin",
        type = "string",
    )
    val password: String,
    @field:Schema(
        description = "Is user activated",
        example = "true",
        type = "boolean",
    )
    val activated: Boolean,
)
