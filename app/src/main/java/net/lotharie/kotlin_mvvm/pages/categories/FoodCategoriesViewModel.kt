package net.lotharie.kotlin_mvvm.pages.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.lotharie.kotlin_mvvm.model.api.DataState
import net.lotharie.kotlin_mvvm.pages.categories.CategoriesUiState.Error
import net.lotharie.kotlin_mvvm.pages.categories.CategoriesUiState.Loading
import net.lotharie.kotlin_mvvm.pages.categories.CategoriesUiState.Success
import net.lotharie.kotlin_mvvm.repository.FoodMenuRepository
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(private val foodMenu: FoodMenuRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<CategoriesUiState>(Loading)
    val uiState: StateFlow<CategoriesUiState> = _uiState

    init {
        viewModelScope.launch {
            // Fetch food categories and update UI state, retry each 2 seconds until successful because example API always down
            while (true) {
                when (val result = foodMenu.getFoodCategories()) {
                    is DataState.Success -> {
                        _uiState.value = Success(result.data)
                        return@launch
                    }
                    is DataState.Error -> {
                        _uiState.value = Error(result.exception)
                        // Retry after 2 seconds
                        delay(2000)
                    }

                    DataState.Loading -> {
                        _uiState.value = Loading
                    }
                }
            }
        }
    }
}



