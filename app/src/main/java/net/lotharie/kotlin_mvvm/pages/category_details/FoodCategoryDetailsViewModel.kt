package net.lotharie.kotlin_mvvm.pages.category_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import net.lotharie.kotlin_mvvm.model.api.DataState
import net.lotharie.kotlin_mvvm.repository.FoodMenuRepository
import net.lotharie.kotlin_mvvm.ui.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCategoryDetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: FoodMenuRepository
) : ViewModel() {

    var state by mutableStateOf(
        FoodCategoryDetailsContract.State(
            null, listOf()
        )
    )
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<Exception?>(null)
        private set

    init {
        viewModelScope.launch {
            val categoryId = stateHandle.get<String>(NavigationKeys.Arg.FOOD_CATEGORY_ID)
                ?: throw IllegalStateException("No categoryId was passed to destination.")

            repository.getFoodCategories().collectLatest { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        isLoading = true
                        error = null
                    }
                    is DataState.Success -> {
                        isLoading = false
                        val category = dataState.data.first { it.id == categoryId }
                        state = state.copy(category = category)
                        // Ensuite, charge les meals
                        repository.getMealsByCategory(categoryId).collectLatest { mealState ->
                            when (mealState) {
                                is DataState.Loading -> isLoading = true
                                is DataState.Success -> {
                                    isLoading = false
                                    state = state.copy(categoryFoodItems = mealState.data)
                                }
                                is DataState.Error -> {
                                    isLoading = false
                                    error = mealState.exception
                                }
                            }
                        }
                    }
                    is DataState.Error -> {
                        isLoading = false
                        error = dataState.exception
                    }
                }
            }
        }
    }
}