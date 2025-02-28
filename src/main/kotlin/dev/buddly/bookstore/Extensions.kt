package dev.buddly.bookstore

import dev.buddly.bookstore.domain.Author
import dev.buddly.bookstore.domain.Book
import dev.buddly.bookstore.dto.AuthorDto
import dev.buddly.bookstore.dto.BookDto

fun Author.toAuthorDto() = AuthorDto (
    id=this.id,
    name=this.name,
    age=this.age,
    description=this.description,
    image=this.image
)

fun AuthorDto.toAuthor() = Author (
    id=this.id,
    name=this.name,
    age=this.age,
    description=this.description,
    image=this.image
)

fun Book.toBookDto() = BookDto(
    isbn=this.isbn,
    title=this.title,
    description=this.description,
    image=this.image,
    author = this.author.toAuthorDto()
)

fun BookDto.toBook(author: Author) = Book(
    isbn=this.isbn,
    title=this.title,
    description=this.description,
    image=this.image,
    author = author
)