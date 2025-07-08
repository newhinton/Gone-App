package de.felixnuesse.gwtbw

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.json.JSONObject


@Serializable
class Comics {


    var comics = arrayListOf<Comic>()

    fun asJSON(): JSONObject {
        return JSONObject(Json.encodeToString(this))
    }

    companion object {
        fun fromJSON(data: String): Comics {
            val json = Json {
                ignoreUnknownKeys = true
                allowTrailingComma = true
            }
            return json.decodeFromString(data)
        }
    }
}