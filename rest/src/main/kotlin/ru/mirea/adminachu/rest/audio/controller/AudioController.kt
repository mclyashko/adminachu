package ru.mirea.adminachu.rest.audio.controller

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.AudioId
import ru.mirea.adminachu.domain.user.User
import ru.mirea.adminachu.rest.audio.contract.AudioContract
import ru.mirea.adminachu.rest.audio.dto.request.AddAudioRequest
import ru.mirea.adminachu.rest.audio.dto.request.UpdateAudioRequest
import ru.mirea.adminachu.rest.audio.dto.request.extension.toAddAudioInfo
import ru.mirea.adminachu.rest.audio.dto.request.extension.toUpdateAudioInfo
import ru.mirea.adminachu.rest.audio.dto.response.AudioResponse
import ru.mirea.adminachu.rest.audio.dto.response.extension.toResponseDto
import ru.mirea.adminachu.rest.util.getSpringUserUserName
import ru.mirea.adminachu.usecase.audio.declaration.command.AddNewAudio
import ru.mirea.adminachu.usecase.audio.declaration.command.RemoveAudioById
import ru.mirea.adminachu.usecase.audio.declaration.command.UpdateAudioById
import ru.mirea.adminachu.usecase.audio.declaration.query.GetAllAudios
import ru.mirea.adminachu.usecase.audio.declaration.query.GetAudioById
import ru.mirea.adminachu.usecase.audio.declaration.query.GetAudioByName
import ru.mirea.adminachu.usecase.audio.dto.RemoveAudioInfo
import ru.mirea.adminachu.usecase.user.declaration.query.GetUserByUserName
import java.util.UUID

private val log = KotlinLogging.logger { }

@RestController
@RequestMapping("/api")
@Suppress("LongParameterList")
class AudioController(
    private val getAllAudios: GetAllAudios,
    private val getAudioById: GetAudioById,
    private val getAudioByName: GetAudioByName,
    private val addNewAudio: AddNewAudio,
    private val updateAudioById: UpdateAudioById,
    private val removeAudioById: RemoveAudioById,
    private val getUserByUserName: GetUserByUserName,
) : AudioContract {
    @GetMapping("/audio")
    override fun getAllAudios(): ResponseEntity<List<AudioResponse>> {
        return ResponseEntity.ok(
            getAllAudios
                .execute()
                .map { audioInfo ->
                    audioInfo.toResponseDto()
                }
        )
    }

    @GetMapping("/audio/id")
    override fun getAudioById(
        @RequestParam id: UUID
    ): ResponseEntity<AudioResponse> {
        log.info {
            "getAudioById with id $id"
        }
        return ResponseEntity.ok(
            getAudioById
                .execute(id = AudioId(id))
                .toResponseDto()
        )
    }

    @GetMapping("/audio/name")
    override fun getAudioByName(
        @RequestParam name: String
    ): ResponseEntity<AudioResponse> {
        log.info {
            "getAudioByName with name $name"
        }
        return ResponseEntity.ok(
            getAudioByName
                .execute(name = Audio.Name(name))
                .toResponseDto()
        )
    }

    @PostMapping("/audio")
    override fun addNewAudio(
        @RequestBody addAudioRequest: AddAudioRequest
    ): ResponseEntity<AudioResponse> {
        log.info {
            "addNewAudio with addAudioRequest $addAudioRequest"
        }

        val user = getUserByUserName
            .execute(userName = User.Name(value = getSpringUserUserName()))

        return ResponseEntity.ok(
            addNewAudio
                .execute(
                    addAudioInfo = addAudioRequest
                        .toAddAudioInfo(createdBy = user.id.toUUIDValue())
                )
                .toResponseDto()
        )
    }

    @PutMapping("/audio/id")
    override fun updateAudio(
        @RequestParam id: UUID,
        @RequestBody updateAudioRequest: UpdateAudioRequest
    ): ResponseEntity<AudioResponse> {
        log.info {
            "updateAudio with id $id updateAudioRequest $updateAudioRequest"
        }

        return ResponseEntity.ok(
            updateAudioById
                .execute(
                    updateAudioInfo = updateAudioRequest.toUpdateAudioInfo(
                        audioId = id,
                    )
                )
                .toResponseDto()
        )
    }

    @DeleteMapping("/audio/id")
    override fun removeAudio(
        @RequestParam id: UUID,
    ): ResponseEntity<AudioResponse> {
        log.info {
            "removeAudio with id $id"
        }

        val user = getUserByUserName
            .execute(userName = User.Name(value = getSpringUserUserName()))

        return ResponseEntity.ok(
            removeAudioById
                .execute(
                    removeAudioInfo = RemoveAudioInfo(
                        audioId = AudioId(value = id),
                        userId = user.id,
                    )
                )
                .toResponseDto()
        )
    }
}
