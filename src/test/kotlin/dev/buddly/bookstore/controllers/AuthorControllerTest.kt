package dev.buddly.bookstore.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import dev.buddly.bookstore.domain.Author
import dev.buddly.bookstore.dto.AuthorDto
import dev.buddly.bookstore.service.AuthorService
import dev.buddly.bookstore.testAuthorDtoA
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    @MockkBean val authorService: AuthorService
) {

    val objectMapper = ObjectMapper()

    @BeforeEach
    fun beforeEach(){
        every {
            authorService.save(any())
        } answers {
            firstArg()
        }
    }

    @Test
    fun `test that create Author saves the Author`(){
        mockMvc.post("/v1/authors") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorDtoA()
            )
        }

        val expected = Author(
            id = null,
            name = "test",
            age = 30,
            description = "test description",
            image = "test image"
        )

        verify { authorService.save(expected) }
    }


    @Test
    fun `test that create Author returns a HTTP 201 status on a successful create`(){
        mockMvc.post("/v1/authors") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorDtoA()
            )
        }.andExpect {
            status { isCreated() }
        }
    }
}