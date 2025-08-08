package net.lotharie.kotlin_mvvm.repository

import android.util.Log
import net.lotharie.kotlin_mvvm.model.CategoryItem
import net.lotharie.kotlin_mvvm.model.MealItem
import net.lotharie.kotlin_mvvm.model.api.DataState
import net.lotharie.kotlin_mvvm.model.api.response.food.FoodCategoriesResponse
import net.lotharie.kotlin_mvvm.model.api.response.food.MealsResponse
import net.lotharie.kotlin_mvvm.pages.category_details.CategoryDetailsData
import net.lotharie.kotlin_mvvm.service.api.FoodMenuApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodMenuRepository @Inject constructor(private val foodMenuApi: FoodMenuApiService) {

    private var cachedCategories: List<CategoryItem>? = null

    suspend fun getFoodCategories(
    ): DataState<List<CategoryItem>> {
        return try {
            Log.d("FoodMenuRepository", "Fetching food categories...")
            val categories =
                cachedCategories ?: foodMenuApi.getFoodCategories().mapCategoriesToItems().also {
                    cachedCategories = it
                }
            Log.d("FoodMenuRepository", "Fetched categories: $categories")
            DataState.Success(categories)
        } catch (e: Exception) {
            Log.e("FoodMenuRepository", "Error fetching food categories: ${e.message}")
            DataState.Error(e)
        }
    }

    suspend fun getMealsByCategory(categoryId: String): DataState<CategoryDetailsData> {
        try {
            val categories =
                cachedCategories ?: foodMenuApi.getFoodCategories().mapCategoriesToItems().also {
                    cachedCategories = it
                }
            val categoryName = categories.first { it.id == categoryId }.name
            val meals = foodMenuApi.getMealsByCategory(categoryName).mapMealsToItems()
            return DataState.Success(
                CategoryDetailsData(
                    category = categories.firstOrNull { it.id == categoryId },
                    categoryMealItems = meals
                )
            )
        } catch (e: Exception) {
            return DataState.Error(e)
        }
    }

    private fun FoodCategoriesResponse.mapCategoriesToItems(): List<CategoryItem> {
        return this.categories.map { category ->
            CategoryItem(
                id = category.id,
                name = category.name,
                description = category.description,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

    private fun MealsResponse.mapMealsToItems(): List<MealItem> {
        return this.meals.map { category ->
            MealItem(
                id = category.id,
                name = category.name,
                thumbnailUrl = category.thumbnailUrl ?: "",
            )
        }
    }
}