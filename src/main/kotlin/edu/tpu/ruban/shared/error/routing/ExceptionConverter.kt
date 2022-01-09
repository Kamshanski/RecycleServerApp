package edu.tpu.ruban.shared.error.routing

import edu.tpu.ruban.shared.error.domain.entity.DomainException
import edu.tpu.ruban.shared.error.domain.entity.RequestException
import javax.inject.Inject

class ExceptionConverter @Inject constructor() {

    operator fun invoke(exception: Throwable): ErrorMessage {
        return when(exception) {
            is DomainException -> convertDomainException(exception)
            is RequestException -> convertRequestException(exception)
            else -> ErrorMessage(null, exceptionMessage = exception.message)
        }
    }

    private fun convertDomainException(exception: DomainException) : ErrorMessage =
        ErrorMessage(
            identifier = exception.type.code.toString(),
            commentMessage = exception.commentMessage,
            exceptionMessage = exception.exceptionMessage,
        )

    private fun convertRequestException(exception: RequestException) : ErrorMessage =
        ErrorMessage(
            identifier = RequestException::class.simpleName,
            commentMessage = exception.commentMessage,
            exceptionMessage = exception.exceptionMessage,
        )

    private fun convertOtherException(exception: Throwable): ErrorMessage =
        ErrorMessage(
            identifier = exception::class.simpleName,
            exceptionMessage = exception.message
        )
}