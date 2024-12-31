package dev.buddly.bookstore.service.impl

import dev.buddly.bookstore.domain.Author
import dev.buddly.bookstore.repo.AuthorRepository
import dev.buddly.bookstore.service.AuthorService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(
    private val authorRepository: AuthorRepository
): AuthorService {

    override fun save(author: Author): Author {
        require(null == author.id)
        return authorRepository.save(author)
    }

    override fun list(): List<Author> {
        return authorRepository.findAll()
    }

    override fun get(id: Long): Author? {
        return authorRepository.findByIdOrNull(id)
    }

    @Transactional
    override fun fullUpdate(id: Long, author: Author): Author {
        check(authorRepository.existsById(id))
        val normalisedAuthor = author.copy(id=id)
        return authorRepository.save(normalisedAuthor)
    }

    override fun delete(id: Long) {
        return authorRepository.deleteById(id)
    }
}