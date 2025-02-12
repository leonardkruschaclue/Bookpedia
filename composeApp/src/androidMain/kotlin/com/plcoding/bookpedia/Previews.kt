package com.plcoding.bookpedia

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState

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
        authors = listOf("Leo"),
        description = "Description$it",
        languages = listOf("EN", "DE"),
        firstPublishYear = "2001",
        ratingAverage = 4.6345,
        ratingCount = 12,
        numPages = 450,
        numEditions = 3
    )
}

@Preview
@Composable
private fun BookListPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = books,
        ),
        onAction = {}
    )
}