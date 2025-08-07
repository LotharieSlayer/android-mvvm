package net.lotharie.kotlin_mvvm.model

data class CategoryItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = ""
)
