package dev.buddly.bookstore.service

import dev.buddly.bookstore.domain.Author

interface AuthorService {
    fun save(author: Author): Author
    fun list(): List<Author>
    fun get(id: Long): Author?
    fun fullUpdate(id:Long, author: Author): Author
    fun delete(id: Long): Unit
}