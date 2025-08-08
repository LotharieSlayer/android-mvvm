package net.lotharie.kotlin_mvvm.ui.navigation

import net.lotharie.kotlin_mvvm.pages.categories.FoodCategoriesBaseRoute
import net.lotharie.kotlin_mvvm.pages.categories.FoodCategoriesRoute
import net.lotharie.kotlin_mvvm.pages.category_details.FoodCategoryDetailsRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    FOOD_CATEGORIES(
        route = FoodCategoriesRoute::class,
        baseRoute = FoodCategoriesBaseRoute::class,
    ),
    FOOD_CATEGORY_DETAILS(
        route = FoodCategoryDetailsRoute::class,
    ),
}
