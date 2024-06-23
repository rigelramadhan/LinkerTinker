package one.reevdev.linkertinker.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import one.reevdev.linkertinker.ui.navigation.CategoryDataCarrier
import one.reevdev.linkertinker.ui.navigation.LinkRoutes
import one.reevdev.linkertinker.ui.screen.categories.CategoriesScreen
import one.reevdev.linkertinker.ui.screen.links.LinksScreen
import one.reevdev.linkertinker.utils.toJson
import one.reevdev.linkertinker.utils.toObject

@Composable
fun LinkerTinkerApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = LinkRoutes.Categories.route,
) {
    val uriHandler = LocalUriHandler.current

    Scaffold {
        NavHost(
            modifier = modifier
                .padding(it),
            startDestination = startDestination,
            navController = navController,
        ) {
            composable(LinkRoutes.Categories.route) {
                CategoriesScreen {
                    val json = it.toJson()
                    navController.navigate(CategoryDataCarrier(json))
                }
            }
            composable<CategoryDataCarrier> { navBackStackEntry ->
                val category = navBackStackEntry.toRoute<CategoryDataCarrier>()
                val data = category.json.toObject()
                LinksScreen(category = data) {
                    uriHandler.openUri(it)
                }
            }
        }
    }
}