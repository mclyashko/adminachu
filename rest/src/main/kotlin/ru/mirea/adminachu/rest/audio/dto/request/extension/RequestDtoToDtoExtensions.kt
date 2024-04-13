package ru.mirea.adminachu.rest.audio.dto.request.extension

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.AudioId
import ru.mirea.adminachu.domain.common.definition.UserId
import ru.mirea.adminachu.rest.audio.dto.request.AddAudioRequest
import ru.mirea.adminachu.rest.audio.dto.request.UpdateAudioRequest
import ru.mirea.adminachu.usecase.audio.dto.AddAudioInfo
import ru.mirea.adminachu.usecase.audio.dto.UpdateAudioInfo
import java.util.UUID

fun AddAudioRequest.toAddAudioInfo(createdBy: UUID): AddAudioInfo = AddAudioInfo(
    name = Audio.Name(name),
    description = Audio.Description(description),
    file = Audio.File(file),
    createdBy = UserId(createdBy),
)

fun UpdateAudioRequest.toUpdateAudioInfo(audioId: UUID): UpdateAudioInfo = UpdateAudioInfo(
    audioId = AudioId(audioId),
    name = Audio.Name(name),
    description = Audio.Description(description),
    file = Audio.File(file),
)
