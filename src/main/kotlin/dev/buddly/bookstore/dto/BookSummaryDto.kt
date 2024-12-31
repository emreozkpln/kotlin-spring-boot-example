package dev.buddly.bookstore.dto

data class BookSummaryDto(
    var isbn: String,
    var title: String,
    var description: String,
    var image: String,
    var author: AuthorSummaryDto
)