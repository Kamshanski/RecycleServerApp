package edu.tpu.ruban.component.gson

import javax.inject.Qualifier

// TODO никаких DI в компонентах. Убрать это в shared.
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GsonConverter(val value: Type)
