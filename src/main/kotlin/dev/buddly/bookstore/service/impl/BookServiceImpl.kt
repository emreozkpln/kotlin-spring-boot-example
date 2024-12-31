package dev.buddly.bookstore.service.impl

import dev.buddly.bookstore.domain.Book
import dev.buddly.bookstore.dto.BookDto
import dev.buddly.bookstore.repo.AuthorRepository
import dev.buddly.bookstore.repo.BookRepository
import dev.buddly.bookstore.service.BookService
import dev.buddly.bookstore.toBook
import dev.buddly.bookstore.toBookDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
): BookService {

    override fun create(isbn: String, bookDto: BookDto): Pair<Book, Boolean> {
        val normalisedBook = bookDto.copy(isbn = isbn)
        val isExists = bookRepository.existsById(isbn)

        val author = authorRepository.findByIdOrNull(bookDto.author.id)
        checkNotNull(author) { "Author with id ${bookDto.author.id} does not exist" }

        val savedBook = bookRepository.save(normalisedBook.toBook(author))
        return Pair(savedBook,!isExists)
    }

    override fun list(): List<BookDto> {
        return bookRepository.findAll().map { it.toBookDto() }
    }


}