package edu.tpu.ruban.core.plugin

import io.ktor.application.Application

interface Plugin {

    fun configure(application: Application)

}