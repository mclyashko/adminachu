package ru.mirea.adminachu.usecase.audio.invariant

import org.springframework.stereotype.Component
import ru.mirea.adminachu.domain.audio.Audio
import ru.mirea.adminachu.domain.audio.declaration.AudioAlreadyExists
import ru.mirea.adminachu.domain.common.util.metric.MetricLogger
import ru.mirea.adminachu.usecase.audio.declaration.acess.AudioExtractor

@Component
class AudioAlreadyExistsByExtractor(
    private val audioExtractor: AudioExtractor,
    private val metricLogger: MetricLogger,
) : AudioAlreadyExists {
    override fun invoke(name: Audio.Name): Boolean {
        val audio = audioExtractor.getByName(name)
        audio?.let {
            metricLogger.registerCounter(
                name = MetricLogger.AUDIO_ALREADY_EXISTS_VALIDATION_FAILED
            )
            return true
        }
        return false
    }
}
