package dev.buddly.bookstore.repo

import dev.buddly.bookstore.domain.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Long?>