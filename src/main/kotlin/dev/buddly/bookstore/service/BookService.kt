package dev.buddly.bookstore.service

import dev.buddly.bookstore.domain.Book
import dev.buddly.bookstore.dto.BookDto


interface BookService {
    fun create(isbn: String, bookDto: BookDto): Pair<Book,Boolean>
    fun list(): List<BookDto>
}