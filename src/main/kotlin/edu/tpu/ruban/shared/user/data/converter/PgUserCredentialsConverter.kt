package edu.tpu.ruban.shared.user.data.converter

import edu.tpu.ruban.component.data.converter.DataConverter
import edu.tpu.ruban.shared.user.data.model.UserModel
import edu.tpu.ruban.shared.user.domain.entity.Credentials

object PgUserCredentialsConverter: DataConverter<Credentials, UserModel> {

    override fun toData(entity: Credentials): UserModel = TODO()

    override fun toDomain(model: UserModel): Credentials {
        TODO("Not yet implemented")
    }
}