package ru.mirea.adminachu.rest.audio.contract

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import ru.mirea.adminachu.rest.audio.dto.request.AddAudioRequest
import ru.mirea.adminachu.rest.audio.dto.request.UpdateAudioRequest
import ru.mirea.adminachu.rest.audio.dto.response.AudioResponse
import java.util.UUID

@OpenAPIDefinition(
    info = Info(
        title = "adminachu",
        version = "1.0.0"
    )
)
interface AudioContract {
    @Operation(
        summary = "Get all audios"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getAllAudios(): ResponseEntity<List<AudioResponse>>

    @Operation(
        summary = "Get audio by id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Audio not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getAudioById(id: UUID): ResponseEntity<AudioResponse>

    @Operation(
        summary = "Get audio by name"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Audio not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getAudioByName(name: String): ResponseEntity<AudioResponse>

    @Operation(
        summary = "Add new audio"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun addNewAudio(addAudioRequest: AddAudioRequest): ResponseEntity<AudioResponse>

    @Operation(
        summary = "Update information about the audio by id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Audio not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun updateAudio(
        id: UUID,
        updateAudioRequest: UpdateAudioRequest
    ): ResponseEntity<AudioResponse>

    @Operation(
        summary = "Mark audio as removed"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Audio not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun removeAudio(
        id: UUID,
    ): ResponseEntity<AudioResponse>
}
