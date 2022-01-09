package edu.tpu.ruban.shared.error.domain.entity

data class Error(
    val exception: Throwable? = null,
    val message: String? = null,
    val code: DomainErrorType,
)