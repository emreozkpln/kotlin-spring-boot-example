package dev.buddly.bookstore.domain

import jakarta.persistence.*

@Entity
@Table(name = "authors")
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    var id: Long?,

    @Column(name = "name")
    var name: String,

    @Column(name = "age")
    var age: Int,

    @Column(name = "description")
    var description: String,

    @Column(name = "image")
    var image: String
)