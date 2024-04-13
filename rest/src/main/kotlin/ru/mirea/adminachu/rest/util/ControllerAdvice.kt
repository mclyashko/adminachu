package ru.mirea.adminachu.rest.util

// @ControllerAdvice()
// class ControllerAdvice {
//    @ExceptionHandler(DomainException::class)
//    fun handleAudioControllerException(
//        ex: DomainException,
//    ): ResponseEntity<Any> {
//        return when (ex) {
//            is DomainException.AudioAlreadyExistsException,
//            is DomainException.UserAlreadyExistsException,
//            -> ResponseEntity(HttpStatus.BAD_REQUEST)
//
//            is DomainException.GetAudioByIdNotFoundException,
//            is DomainException.GetAudioByNameNotFoundException,
//            is DomainException.RemoveAudioByIdNotFoundException,
//            is DomainException.UpdateAudioByIdNotFoundException,
//            is DomainException.DisableUserByIdNotFoundException,
//            is DomainException.EnableUserByIdNotFoundException,
//            is DomainException.GetUserByEmailNotFoundException,
//            is DomainException.GetUserByUserNameNotFoundException,
//            is DomainException.UpdateUserByIdNotFoundException,
//            -> ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }
// }
