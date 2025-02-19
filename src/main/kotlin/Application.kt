
import appModule.featureService
import appModule.historicoService
import freemarker.cache.ClassTemplateLoader
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.freemarker.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import appModule.projetoService
import appModule.visitLogService
import infrastructure.api.routes.*
import infrastructure.session.configureSessions
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import kotlinx.coroutines.launch

fun Application.module() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        templateUpdateDelayMilliseconds = 100
    }



    configureSessions()
    configureTracking()

    routing {
        projetoRoutes(projetoService)
        mainRoutes(projetoService)
        featureRoutes(featureService)
        AuthRoutes()
        adminRoutes()
        historicoRoutes(historicoService)
    }
}

fun Application.configureTracking(){
    intercept(ApplicationCallPipeline.Monitoring){

        val ipAddress = call.request.origin.remoteHost
        val page = call.request.uri

        val ignore_paths = listOf(
            "/static",
            "/favicon.ico"
        )

        if(ignore_paths.any{ page.startsWith(it) }) {
            return@intercept
        }

        launch {
            visitLogService.create(
                ipAddress = ipAddress,
                page = page
            )
        }
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