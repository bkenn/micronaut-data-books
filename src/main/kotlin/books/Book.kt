package books

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Book(
        @Column(name = "book_name")
        var name: String,

        @Id
        @Column(name = "book_id")
        @GeneratedValue
        var id: Long = -1
)

@JdbcRepository(dialect = Dialect.H2)
interface BookRepository : CrudRepository<Book, Long>

@Controller("/book")
class BookController(private val bookRepository: BookRepository) {

    @Get("/{name}")
    fun create(name: String): Book = bookRepository.save(Book(name))

    @Get("/")
    fun findAll(): Iterable<Book> = bookRepository.findAll()

}

@Singleton
class BookDataLoader(private val bookRepository: BookRepository) {

    fun load() {
        LOG.info("Populating data")

        val b1 = Book("Moby Dick")
        val b2 = Book("Great Expectations")
        bookRepository.saveAll(listOf(b1, b2))
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(BookDataLoader::class.java)
    }

}