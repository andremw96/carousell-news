package com.andremw96.carousell_news.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremw96.carousell_news.mapper.CarousellNewsSchemaToState
import com.andremw96.core.data.Resource
import com.andremw96.core.di.IoDispatcher
import com.andremw96.core.domain.usecase.GetCarousellNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
class CarousellNewsListViewModel @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val getCarousellNews: GetCarousellNews,
    private val carousellNewsSchemaToState: CarousellNewsSchemaToState,
) : ViewModel(), CarousellNewsListCallbacks {
    private val _viewState: MutableStateFlow<CarousellNewsListViewState> = MutableStateFlow(
        CarousellNewsListViewState.initialState()
    )
    val viewState = _viewState.asStateFlow()

    override fun loadCarousellNews() {
        viewModelScope.launch(coroutineDispatcher) {
            getCarousellNews().collect {
                when (it) {
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            carousellNews = emptyList(),
                            isLoading = true,
                            errorMessage = null,
                        )
                    }

                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            carousellNews = emptyList(),
                            isLoading = false,
                            errorMessage = it.message,
                        )
                    }

                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            carousellNews = carousellNewsSchemaToState(it.data ?: emptyList()),
                            isLoading = false,
                            errorMessage = null,
                        )
                    }
                }
            }
        }
    }
}
