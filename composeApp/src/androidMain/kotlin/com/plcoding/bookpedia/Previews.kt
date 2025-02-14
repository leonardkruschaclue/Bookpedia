package com.plcoding.bookpedia

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState
import com.plcoding.bookpedia.core.domain.Optional

//@Preview
//@Composable
//private fun BookSearchBarPreview() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White)
//    ) {
//        BookSearchBar(searchQuery = "", onSearchQueryChange = {}, onImeSearch = {})
//    }
//}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book$it",
        imageUrl = "test",
        authors = Optional.Just(listOf("Leo")),
        description = Optional.Just("Description$it"),
        languages = listOf("EN", "DE"),
        firstPublishYear = Optional.Just("2001"),
        ratingAverage = Optional.Just(4.6345),
        ratingCount = Optional.Just(12),
        numPages = Optional.Just(450),
        numEditions = 3
    )
}

@Preview
@Composable
private fun BookListPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = books,
            isLoading = false
        ),
        onAction = {}
    )
}