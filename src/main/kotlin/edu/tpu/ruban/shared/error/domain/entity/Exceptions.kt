package edu.tpu.ruban.shared.error.domain.entity

class DomainException(val type: DomainErrorType, val exceptionMessage: String, val commentMessage: String? = null, cause: Throwable? = null) :
    Exception(exceptionMessage, cause)

class RequestException(val exceptionMessage: String, val commentMessage: String? = null, cause: Throwable? = null) : Exception(exceptionMessage, cause)