package edu.tpu.ruban.component.route

import edu.tpu.ruban.component.path.SlashPath

// TODO перенести в shared, т.к. это информация касаетс только фич и shared.
//  И может как-н заменить object на что-то дургое.
//  Можно enum или вообще в виде Destination в отдельных модулях
object Paths {
    val API_PATH = SlashPath("api")
}