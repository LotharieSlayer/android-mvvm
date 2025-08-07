package net.lotharie.kotlin_mvvm.pages.category_details

import net.lotharie.kotlin_mvvm.model.CategoryItem
import net.lotharie.kotlin_mvvm.model.MealItem

sealed interface CategoryDetailsUiState {
    data object Loading : CategoryDetailsUiState
    data class Success(
        val categoryDetailsData: CategoryDetailsData,
    ) : CategoryDetailsUiState
    data class Error(
        val exception: Exception,
    ) : CategoryDetailsUiState
}

data class CategoryDetailsData(
    val category: CategoryItem?,
    val categoryMealItems: List<MealItem>
)