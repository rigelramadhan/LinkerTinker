package one.reevdev.linkertinker.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LinkData(
    val name: String = "",
    val url: String = "",
)
