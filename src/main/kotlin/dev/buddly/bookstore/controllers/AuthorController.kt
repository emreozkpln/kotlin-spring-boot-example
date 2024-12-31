package dev.buddly.bookstore.controllers

import dev.buddly.bookstore.domain.Author
import dev.buddly.bookstore.dto.AuthorDto
import dev.buddly.bookstore.service.AuthorService
import dev.buddly.bookstore.toAuthor
import dev.buddly.bookstore.toAuthorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/authors"])
class AuthorController(
    private val authorService: AuthorService
) {

    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        val createdAuthor =authorService.save(authorDto.toAuthor()).toAuthorDto()
        return ResponseEntity(createdAuthor,HttpStatus.CREATED)
    }

    @GetMapping
    fun readManyAuthor(): List<AuthorDto> {
        return authorService.list().map { it.toAuthorDto() }
    }

    @GetMapping(path = ["/{id}"])
    fun readOneAuthor(@PathVariable id: Long): ResponseEntity<AuthorDto> {
        val foundAuthor = authorService.get(id)?.toAuthorDto()
        return foundAuthor?.let {
            ResponseEntity(it,HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping(path = ["/{id}"])
    fun fullUpdate(@PathVariable id:Long, @RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val updatedAuthor = authorService.fullUpdate(id,authorDto.toAuthor())
            ResponseEntity(updatedAuthor.toAuthorDto(),HttpStatus.OK)
        }catch(ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun delete(@PathVariable id:Long):ResponseEntity<Unit>{
        authorService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}