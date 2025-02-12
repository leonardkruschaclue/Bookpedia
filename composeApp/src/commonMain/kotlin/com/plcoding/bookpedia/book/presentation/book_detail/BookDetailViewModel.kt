package com.plcoding.bookpedia.book.presentation.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.plcoding.bookpedia.app.Routes
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val bookId = savedStateHandle.toRoute<Routes.BookDetail>().id
    private val _state = MutableStateFlow(BookDetailsState())
    val state = _state.onStart { fetchBookDescription() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)


    fun onAction(action: BookDetailAction) {
        when (action) {
            is BookDetailAction.OnSelectedBookChange -> _state.update { it.copy(book = action.book) }
            BookDetailAction.OnFavoriteClick -> Unit
            else -> Unit
        }
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            bookRepository.getBookDescription(bookId).onSuccess { description ->
                _state.update {
                    it.copy(
                        book = it.book?.copy(
                            description = description
                        ),
                        isLoading = false
                    )
                }
            }
        }
    }
}