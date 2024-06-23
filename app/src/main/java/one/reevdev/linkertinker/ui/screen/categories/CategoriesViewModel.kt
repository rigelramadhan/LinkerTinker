package one.reevdev.linkertinker.ui.screen.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.linkertinker.core.data.LinkRepository
import one.reevdev.linkertinker.core.data.Resource.Failure
import one.reevdev.linkertinker.core.data.Resource.Loading
import one.reevdev.linkertinker.core.data.Resource.Success
import one.reevdev.linkertinker.core.data.model.LinkCategory
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: LinkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState: StateFlow<CategoriesUiState> by lazy { _uiState }

    fun getAllCategories() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getAllCategories()
                .catch { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.localizedMessage,
                        )
                    }
                }
                .collect { data ->
                    _uiState.update {
                        when (data) {
                            is Loading -> {
                                it.copy(
                                    isLoading = true
                                )
                            }

                            is Success -> {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = null,
                                    categories = data.data
                                )
                            }

                            is Failure -> {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = data.message
                                )
                            }
                        }
                    }
                }
        }

    }

    fun addCategory(category: LinkCategory) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.addCategory(category)
                .catch { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.localizedMessage,
                        )
                    }
                }
                .collect { data ->
                    _uiState.update {
                        when (data) {
                            is Loading -> {
                                it.copy(
                                    isLoading = true
                                )
                            }

                            is Success -> {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = null,
                                )
                            }

                            is Failure -> {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = data.message
                                )
                            }
                        }
                    }
                }
        }
    }
}

data class CategoriesUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val categories: List<LinkCategory> = emptyList(),
)