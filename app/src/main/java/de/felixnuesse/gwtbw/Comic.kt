package de.felixnuesse.gwtbw

import kotlinx.serialization.Serializable


@Serializable
class Comic {
    var id: Int = 0
    var url: String = ""
    var title: String = ""
    var img: String = ""
}