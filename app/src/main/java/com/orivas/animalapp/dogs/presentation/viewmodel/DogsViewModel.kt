package com.orivas.animalapp.dogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orivas.animalapp.ui.UiState
import com.orivas.dogs.di.GetDogsUseCaseQ
import com.orivas.dogs.domain.model.DogsDto
import com.orivas.shared.domain.NetworkResult
import com.orivas.shared.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    @GetDogsUseCaseQ val getDogsUseCase: UseCase<Nothing, DogsDto>
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<DogsDto>>(UiState.None)
    val uiState: StateFlow<UiState<DogsDto>> = _uiState

    private fun updateState(newState: UiState<DogsDto>) {
        _uiState.value = newState
    }

    init {
        loadDogs()
    }

    private fun loadDogs() {
        viewModelScope.launch {
            getDogsUseCase.execute()
                .collect { result ->
                    when (result) {
                        is NetworkResult.OnSuccess -> {
                            updateState(
                                UiState.Success(result.data)
                            )
                        }

                        is NetworkResult.OnError -> {
                            updateState(
                                UiState.Error(
                                    result.exception.message.toString()
                                )
                            )
                        }

                        is NetworkResult.Loading -> {
                            updateState(UiState.Loading)
                        }
                    }
                }
        }
    }
}