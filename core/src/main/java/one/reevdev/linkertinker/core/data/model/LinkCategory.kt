package one.reevdev.linkertinker.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LinkCategory(
    val name: String = "",
    val links: List<LinkData> = emptyList(),
)
