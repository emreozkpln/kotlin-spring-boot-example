package dev.buddly.bookstore.repo

import dev.buddly.bookstore.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<dev.buddly.bookstore.domain.Book, String> {

    fun findByAuthorId(id: Long): List<Book>
}