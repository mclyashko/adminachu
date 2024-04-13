package ru.mirea.adminachu.db.audio.entity.extension

import ru.mirea.adminachu.db.audio.entity.AudioEntity
import ru.mirea.adminachu.db.audio.entity.BroadcastEntity
import ru.mirea.adminachu.db.audio.entity.SequenceEntity
import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.common.definition.AudioId

fun Audio.toAudioEntity(): AudioEntity = AudioEntity(
    id = id.toUUIDValue(),
    name = name.toStringValue(),
    description = description.toStringValue(),
    file = file.toByteArrayValue(),
    createdBy = createdBy.toUUIDValue(),
    removedBy = removedBy?.toUUIDValue(),
)

fun Audio.Broadcast.toBroadcastEntity(audioId: AudioId): BroadcastEntity = BroadcastEntity(
    id = id.toUUIDValue(),
    streamedAt = streamedAt.toInstantValue(),
    audioId = audioId.toUUIDValue(),
)

fun Audio.Sequence.toSequenceEntity(audioId: AudioId): SequenceEntity = SequenceEntity(
    id = id.toUUIDValue(),
    order = order.toLongValue(),
    audioId = audioId.toUUIDValue(),
)
