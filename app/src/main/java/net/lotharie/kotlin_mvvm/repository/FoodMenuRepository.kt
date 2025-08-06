package net.lotharie.kotlin_mvvm.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import net.lotharie.kotlin_mvvm.model.FoodItem
import net.lotharie.kotlin_mvvm.model.api.DataState
import net.lotharie.kotlin_mvvm.model.api.response.food.FoodCategoriesResponse
import net.lotharie.kotlin_mvvm.model.api.response.food.MealsResponse
import net.lotharie.kotlin_mvvm.service.api.FoodMenuApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodMenuRepository @Inject constructor(private val foodMenuApi: FoodMenuApiService) {

    private var cachedCategories: List<FoodItem>? = null

    fun getFoodCategories(): Flow<DataState<List<FoodItem>>> = flow {
        emit(DataState.Loading)
        Log.d("FoodMenuRepository", "Fetching food categories...")
        try {
            var categories = cachedCategories
            if (categories == null) {
                categories = foodMenuApi.getFoodCategories().mapCategoriesToItems()
                Log.d("FoodMenuRepository", "Fetched categories from API: $categories")
                cachedCategories = categories
            }
            emit(DataState.Success(categories))
        } catch (e: Exception) {
            Log.d("FoodMenuRepository", "Error fetching food categories", e)
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    fun getMealsByCategory(categoryId: String): Flow<DataState<List<FoodItem>>> = flow {
        emit(DataState.Loading)
        try {
            val categories = cachedCategories ?: foodMenuApi.getFoodCategories().mapCategoriesToItems().also {
                cachedCategories = it
            }
            val categoryName = categories.first { it.id == categoryId }.name
            val meals = foodMenuApi.getMealsByCategory(categoryName).mapMealsToItems()
            emit(DataState.Success(meals))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    private fun FoodCategoriesResponse.mapCategoriesToItems(): List<FoodItem> {
        return this.categories.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                description = category.description,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

    private fun MealsResponse.mapMealsToItems(): List<FoodItem> {
        return this.meals.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                thumbnailUrl = category.thumbnailUrl ?: "",
            )
        }
    }
}