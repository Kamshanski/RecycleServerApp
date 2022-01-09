package edu.tpu.ruban.shared.error.domain.entity

enum class DomainErrorType(val code: Int) {

    // REGISTRATION
    USER_ALREADY_EXISTS(1),

    // DEVELOPEMENT ERRORS
    NOT_IMPLEMENTED(-100),
    TOKEN_MALFORMATION(-101),

    // UNKNOWN ERROR
    UNKNOWN_ERROR(0),
}