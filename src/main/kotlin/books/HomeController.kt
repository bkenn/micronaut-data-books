package books

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/home")
class HomeController {

    @Get("/")
    fun findAll() = listOf("Option 1", "Option 2")

}