package edu.tpu.ruban.component.ui.routing.response

interface ResponseFactory<T> {

    fun create(result: T): Any
}