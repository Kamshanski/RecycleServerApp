package edu.tpu.ruban.core

import edu.tpu.ruban.core.di.DaggerAppComponent


fun main() {
    val appComponent = DaggerAppComponent.factory().create()
    val app = appComponent.application()
    app.run()
}
