package net.lotharie.kotlin_mvvm.pages.category_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.lotharie.kotlin_mvvm.model.api.DataState
import net.lotharie.kotlin_mvvm.repository.FoodMenuRepository
import net.lotharie.kotlin_mvvm.ui.navigation.NavigationKeys
import javax.inject.Inject

@HiltViewModel
class FoodCategoryDetailsViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    repository: FoodMenuRepository
) : ViewModel() {

    val categoryId = stateHandle.get<String>(NavigationKeys.Arg.FOOD_CATEGORY_ID) ?: ""
    val categoryDetailsUiState: StateFlow<CategoryDetailsUiState> =
        repository.getMealsByCategory(categoryId)
            .map { dataState ->
                when (dataState) {
                    is DataState.Loading -> CategoryDetailsUiState.Loading
                    is DataState.Success -> CategoryDetailsUiState.Success(dataState.data)
                    is DataState.Error -> CategoryDetailsUiState.Error(dataState.exception)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CategoryDetailsUiState.Loading
            )

    init {
        viewModelScope.launch { }
    }
}