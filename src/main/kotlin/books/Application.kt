package books

import io.micronaut.context.event.StartupEvent
import io.micronaut.core.annotation.TypeHint
import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.event.annotation.EventListener
import javax.inject.Singleton

@Singleton
@TypeHint(typeNames = ["org.h2.Driver", "org.h2.mvstore.db.MVTableEngine"])
class Application(private val bookDataLoader: BookDataLoader) {

    @EventListener
    fun init(event: StartupEvent) {
        bookDataLoader.load()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Micronaut.build()
                    .args(*args)
                    .packages("books")
                    .start()
        }
    }
}