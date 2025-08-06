package net.lotharie.kotlin_mvvm.model.api.response.food_menu

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val thumbnailUrl: String?,
)

data class MealsResponse(val meals: List<MealResponse>)
