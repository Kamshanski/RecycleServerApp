package edu.tpu.ruban.component.state

import edu.tpu.ruban.component.path.Path
import edu.tpu.ruban.component.ui.presentation.model.Result

// У Redirect могут появиться параметры

sealed interface ProceedAction {
    class Redirect(val path: Path): ProceedAction
    class Return(val payload: Result): ProceedAction
}