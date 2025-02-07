
import appModule.featureService
import appModule.historicoService
import freemarker.cache.ClassTemplateLoader
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.freemarker.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import appModule.projetoService
import infrastructure.api.routes.*
import infrastructure.session.configureSessions

fun Application.module() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        templateUpdateDelayMilliseconds = 100
    }



    configureSessions()

    routing {
        projetoRoutes(projetoService)
        mainRoutes(projetoService)
        featureRoutes(featureService)
        AuthRoutes()
        adminRoutes()
        historicoRoutes(historicoService)
    }
}

fun main(args: Array<String>){
    try{
        embeddedServer(
            factory = Netty,
            configure = {
                val cliConfig = CommandLineConfig(args)
                takeFrom(cliConfig.engineConfig)
                loadCommonConfiguration(cliConfig.rootConfig.environment.config)
            }
        ) {
            module()
        }.start(wait = true)
    }catch (
        e: Exception){
        println("Erro ao iniciar o servidor: ${e.message}")
    }
}