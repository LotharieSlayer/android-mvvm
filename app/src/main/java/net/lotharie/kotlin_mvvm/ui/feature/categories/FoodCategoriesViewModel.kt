package net.lotharie.kotlin_mvvm.ui.feature.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import net.lotharie.kotlin_mvvm.repository.FoodMenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.lotharie.kotlin_mvvm.model.api.DataState
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(private val foodMenu: FoodMenuRepository) :
    ViewModel() {

    var state by mutableStateOf(
        FoodCategoriesContract.State(
            categories = listOf(),
            isLoading = true
        )
    )
        private set

    var effects = Channel<FoodCategoriesContract.Effect>(UNLIMITED)
        private set

    init {
        viewModelScope.launch { getFoodCategories() }
    }

    private suspend fun getFoodCategories() {
        foodMenu.getFoodCategories().collectLatest { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    state = state.copy(isLoading = true)
                }
                is DataState.Success -> {
                    state = state.copy(categories = dataState.data, isLoading = false)
                    effects.send(FoodCategoriesContract.Effect.DataWasLoaded)
                }
                is DataState.Error -> {
                    state = state.copy(isLoading = false)
                }
            }
        }
    }
}



