package ru.mirea.adminachu.rest.audio.dto.response.extension

import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.rest.audio.dto.response.AudioResponse
import ru.mirea.adminachu.usecase.audio.dto.AudioInfo

fun Audio.Broadcast.toResponseDto(): AudioResponse.Broadcast = AudioResponse.Broadcast(
    id = id.toUUIDValue(),
    streamedAt = streamedAt.toInstantValue(),
)

fun Audio.Sequence.toResponseDto(): AudioResponse.Sequence = AudioResponse.Sequence(
    id = id.toUUIDValue(),
    order = order.toLongValue(),
)

fun AudioInfo.toResponseDto(): AudioResponse = AudioResponse(
    id = id.toUUIDValue(),
    name = name.toStringValue(),
    description = description.toStringValue(),
    file = file.toByteArrayValue(),
    createdBy = createdBy.toUUIDValue(),
    removedBy = removedBy?.toUUIDValue(),
    broadcasts = broadcasts.map { it.toResponseDto() },
    sequences = sequences.map { it.toResponseDto() }
)
