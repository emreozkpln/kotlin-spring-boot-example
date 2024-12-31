package dev.buddly.bookstore.controllers

import dev.buddly.bookstore.dto.BookDto
import dev.buddly.bookstore.dto.BookSummaryDto
import dev.buddly.bookstore.exception.InvalidAuthorException
import dev.buddly.bookstore.service.BookService
import dev.buddly.bookstore.toBookDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/books"])
class BookController(val bookService: BookService) {

    @PutMapping(path = ["/{isbn}"])
    fun createFullUpdateBook(
        @PathVariable("isbn") isbn: String,
        @RequestBody book: BookDto
    ): ResponseEntity<BookDto> {
        try {
            val (savedBook, isCreated) = bookService.create(isbn, book)
            val responseCode = if (isCreated) HttpStatus.CREATED else HttpStatus.OK
            return ResponseEntity(savedBook.toBookDto(), responseCode)
        } catch (ex: InvalidAuthorException) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (ex: IllegalStateException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun readManyBook(): ResponseEntity<List<BookDto>> {
        return ResponseEntity(bookService.list(), HttpStatus.OK)
    }
}