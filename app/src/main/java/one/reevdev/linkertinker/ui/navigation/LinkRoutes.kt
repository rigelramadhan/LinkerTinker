package one.reevdev.linkertinker.ui.navigation

import kotlinx.serialization.Serializable

sealed class LinkRoutes(val route: String) {
    data object Categories : LinkRoutes("categories")
}

@Serializable
data class CategoryDataCarrier(val json: String)