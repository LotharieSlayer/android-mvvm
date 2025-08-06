package net.lotharie.kotlin_mvvm.service.api

import net.lotharie.kotlin_mvvm.model.api.response.food_menu.FoodCategoriesResponse
import net.lotharie.kotlin_mvvm.model.api.response.food_menu.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodMenuApiService @Inject constructor(private val service: ApiService) {

    suspend fun getFoodCategories(): FoodCategoriesResponse = service.getFoodCategories()
    suspend fun getMealsByCategory(categoryId: String): MealsResponse =
        service.getMealsByCategory(categoryId)

    interface ApiService {
        @GET("categories.php")
        suspend fun getFoodCategories(): FoodCategoriesResponse

        @GET("filter.php")
        suspend fun getMealsByCategory(@Query("c") categoryId: String): MealsResponse
    }

    companion object {
        const val API_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}