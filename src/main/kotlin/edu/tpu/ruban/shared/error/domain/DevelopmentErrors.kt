package edu.tpu.ruban.shared.error.domain

import edu.tpu.ruban.shared.error.domain.entity.DomainErrorType
import edu.tpu.ruban.shared.error.domain.entity.DomainException

fun NotImplementedError(detailedMessage: String = "This operation is not implemented yet") = DomainException(
    DomainErrorType.NOT_IMPLEMENTED,
    detailedMessage
)