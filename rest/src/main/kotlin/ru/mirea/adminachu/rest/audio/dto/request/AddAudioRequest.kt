package ru.mirea.adminachu.rest.audio.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for adding a new audio.")
data class AddAudioRequest(
    @field:Schema(
        description = "Audio name",
        example = "Song",
        type = "string",
    )
    val name: String,
    @field:Schema(
        description = "Audio description",
        example = "Good song",
        type = "string",
    )
    val description: String,
    @field:Schema(
        description = "Audio file",
        example = "[0, 1, 0]",
        type = "byte array",
    )
    val file: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddAudioRequest

        if (name != other.name) return false
        if (description != other.description) return false
        if (!file.contentEquals(other.file)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + file.contentHashCode()
        return result
    }
}
