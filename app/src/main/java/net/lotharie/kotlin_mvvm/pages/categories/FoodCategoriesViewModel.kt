package net.lotharie.kotlin_mvvm.pages.categories

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
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(private val foodMenu: FoodMenuRepository) :
    ViewModel() {

    val categoriesUiState: StateFlow<CategoriesUiState> =
        foodMenu.getFoodCategories().map { dataState ->
            when (dataState) {
                is DataState.Loading -> CategoriesUiState.Loading
                is DataState.Success -> CategoriesUiState.Success(dataState.data)
                is DataState.Error -> CategoriesUiState.Error(dataState.exception)
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CategoriesUiState.Loading
            )

    init {
        viewModelScope.launch { }
    }
}



