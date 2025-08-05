package net.lotharie.kotlin_mvvm.ui.feature.categories

import net.lotharie.kotlin_mvvm.model.FoodItem


class FoodCategoriesContract {

    data class State(
        val categories: List<FoodItem> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}