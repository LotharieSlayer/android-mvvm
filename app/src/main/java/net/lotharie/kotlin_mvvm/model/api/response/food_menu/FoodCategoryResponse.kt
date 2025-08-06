package net.lotharie.kotlin_mvvm.model.api.response.food_menu

import com.google.gson.annotations.SerializedName

data class FoodCategoryResponse(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb") val thumbnailUrl: String,
    @SerializedName("strCategoryDescription") val description: String = ""
)

data class FoodCategoriesResponse(val categories: List<FoodCategoryResponse>)
