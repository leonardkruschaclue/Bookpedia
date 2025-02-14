package com.plcoding.bookpedia.book.data.mappers

import com.plcoding.bookpedia.book.data.database.BookEntity
import com.plcoding.bookpedia.book.data.dto.SearchedBookDto
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.domain.Optional
import com.plcoding.bookpedia.core.domain.orElse
import com.plcoding.bookpedia.core.domain.toOptional

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) "https://covers.openlibrary.org/b/olid/$coverKey-L.jpg"
        else "https://covers.openlibrary.org/b/olid/$coverAlternativeKey-L.jpg",
        authors = authorNames.toOptional(),
        description = Optional.None,
        languages = languages ?: emptyList(),
        firstPublishYear = firstPublishYear.toString().toOptional(),
        ratingAverage = ratingAverage.toOptional(),
        ratingCount = ratingCount.toOptional(),
        numPages = numPagesMedian.toOptional(),
        numEditions = numEditions ?: 0
    )
}

fun Book.toBookEntity(): BookEntity{
    return BookEntity(
        id = id,
        title = title,
        description = description.orElse(null),
        imageUrl = imageUrl,
        language = languages,
        authors = authors.orElse(null),
        firstPublishYear = firstPublishYear.orElse(null),
        ratingAverage = ratingAverage.orElse(null),
        ratingCount = ratingCount.orElse(null),
        numPagesMedian = numPages.orElse(null),
        numEditions = numEditions
    )
}

fun BookEntity.toBook(): Book{
    return Book(
        id = id,
        title = title,
        description = description.toOptional(),
        imageUrl = imageUrl,
        languages = language,
        authors = authors.toOptional(),
        firstPublishYear = firstPublishYear.toOptional(),
        ratingAverage = ratingAverage.toOptional(),
        ratingCount = ratingCount.toOptional(),
        numPages = numPagesMedian.toOptional(),
        numEditions = numEditions
    )
}