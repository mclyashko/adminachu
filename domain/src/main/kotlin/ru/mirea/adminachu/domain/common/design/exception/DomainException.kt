package ru.mirea.adminachu.domain.common.design.exception

sealed class DomainException(
    message: String?,
    cause: Throwable?,
) : RuntimeException(message, cause) {

    // Domain exceptions

    class AudioAlreadyExistsException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class UserAlreadyExistsException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    // Use-case exception

    class GetAudioByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class GetAudioByNameNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class UpdateAudioByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class RemoveAudioByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class GetUserByUserNameNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class GetUserByEmailNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class UpdateUserByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class DisableUserByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class EnableUserByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)
}
