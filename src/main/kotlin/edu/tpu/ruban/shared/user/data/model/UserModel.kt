package edu.tpu.ruban.shared.user.data.model

class UserModel(
    val userId: Long,
    val login: String,
    val hash: String,
    val isAdmin: Boolean,
    val isBanned: Boolean,
)