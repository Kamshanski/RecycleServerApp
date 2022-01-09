package edu.tpu.ruban.shared.user.domain.entity

class User(
    val id: Long,
    val login: String,
    val hash: String,
    val isAdmin: Boolean,
    val isBanned: Boolean
//    val creationTime: LocalDateTime
)