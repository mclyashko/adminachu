package ru.mirea.adminachu.domain.common.util.metric

interface MetricLogger {
    fun registerCounter(name: String)

    companion object {
        const val AUDIO_ALREADY_EXISTS_VALIDATION_FAILED =
            "audio.alreadyexistsvalidation.failed"
        const val USER_ALREADY_EXISTS_VALIDATION_FAILED =
            "audio.alreadyexistsvalidation.failed"
    }
}
