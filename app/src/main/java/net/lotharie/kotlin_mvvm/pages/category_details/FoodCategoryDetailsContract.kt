package net.lotharie.kotlin_mvvm.pages.category_details


import net.lotharie.kotlin_mvvm.model.FoodItem


class FoodCategoryDetailsContract {
    data class State(
        val category: FoodItem?,
        val categoryFoodItems: List<FoodItem>
    )
}