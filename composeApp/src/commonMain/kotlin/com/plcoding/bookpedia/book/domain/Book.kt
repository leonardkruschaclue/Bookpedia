package com.plcoding.bookpedia.book.domain

import com.plcoding.bookpedia.core.domain.Optional

data class Book(
    val id: String,
    val title: String,
    val imageUrl: String,
    val authors: Optional<List<String>>,
    val description: Optional<String>,
    val languages: List<String>,
    val firstPublishYear: Optional<String>,
    val ratingAverage: Optional<Double>,
    val ratingCount: Optional<Int>,
    val numPages: Optional<Int>,
    val numEditions: Int
)
