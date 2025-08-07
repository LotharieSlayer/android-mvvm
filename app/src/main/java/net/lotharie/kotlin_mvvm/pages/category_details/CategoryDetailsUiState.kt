package net.lotharie.kotlin_mvvm.pages.category_details

import net.lotharie.kotlin_mvvm.model.FoodItem

sealed interface CategoryDetailsUiState {
    data object Loading : CategoryDetailsUiState
    data class Success(
//        val category: Category?,
        val category: FoodItem?,
        val categoryFoodItems: List<FoodItem>
    ) : CategoryDetailsUiState
    data class Error(
        val exception: Exception,
    ) : CategoryDetailsUiState
}