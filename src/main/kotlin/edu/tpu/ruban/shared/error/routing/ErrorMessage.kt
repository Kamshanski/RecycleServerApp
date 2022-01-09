package edu.tpu.ruban.shared.error.routing

/**
 * @property identifier some id of error. gives
 */
class ErrorMessage(
    val identifier: String? = null,
    val commentMessage: String? = null,
    val exceptionMessage: String? = null,
) {
}