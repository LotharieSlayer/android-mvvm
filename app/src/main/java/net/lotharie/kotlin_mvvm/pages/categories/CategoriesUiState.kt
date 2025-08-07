package net.lotharie.kotlin_mvvm.pages.categories

import net.lotharie.kotlin_mvvm.model.FoodItem

sealed interface CategoriesUiState {
    data object Loading : CategoriesUiState
    data class Success(
        val categories: List<FoodItem>,
    ) : CategoriesUiState
    data class Error(
        val exception: Exception,
    ) : CategoriesUiState
}