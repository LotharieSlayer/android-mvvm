package net.lotharie.kotlin_mvvm.model

data class MealItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = ""
)
