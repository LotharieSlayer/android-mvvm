package net.lotharie.kotlin_mvvm.pages.category_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.lotharie.kotlin_mvvm.model.api.DataState
import net.lotharie.kotlin_mvvm.pages.category_details.CategoryDetailsUiState.Error
import net.lotharie.kotlin_mvvm.pages.category_details.CategoryDetailsUiState.Loading
import net.lotharie.kotlin_mvvm.pages.category_details.CategoryDetailsUiState.Success
import net.lotharie.kotlin_mvvm.repository.FoodMenuRepository
import net.lotharie.kotlin_mvvm.ui.NavigationKeys
import javax.inject.Inject

@HiltViewModel
class FoodCategoryDetailsViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    foodMenu: FoodMenuRepository
) : ViewModel() {

    val categoryId = stateHandle.get<String>(NavigationKeys.Arg.FOOD_CATEGORY_ID) ?: ""
    private val _uiState = MutableStateFlow<CategoryDetailsUiState>(Loading)
    val uiState: StateFlow<CategoryDetailsUiState> = _uiState

    init {
        viewModelScope.launch (Dispatchers.IO) {
            when (val dataState = foodMenu.getMealsByCategory(categoryId)) {
                is DataState.Success -> _uiState.value = Success(dataState.data)
                is DataState.Error -> _uiState.value = Error(dataState.exception)
                DataState.Loading -> _uiState.value = Loading
            }
        }
    }
}