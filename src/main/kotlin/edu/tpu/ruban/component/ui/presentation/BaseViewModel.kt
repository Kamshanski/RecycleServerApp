package edu.tpu.ruban.component.ui.presentation

import edu.tpu.ruban.component.ui.presentation.model.Result
import edu.tpu.ruban.shared.error.routing.ErrorMessage

abstract class BaseViewModel {

    fun success(payload: Any) = Result(
        payload = payload,
        success = true
    )

    fun error(message: ErrorMessage) = Result(
        payload = message,
        success = false
    )
}