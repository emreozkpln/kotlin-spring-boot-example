package dev.buddly.bookstore

import dev.buddly.bookstore.dto.AuthorDto

fun testAuthorDtoA(id: Long? = null) = AuthorDto(
    id = id,
    name = "test",
    age = 30,
    description = "test description",
    image = "test image"
)