package infrastructure.auth

import aplication.services.AuthService
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.application.ApplicationCallPipeline.ApplicationPhase.Plugins
import io.ktor.server.response.*
import io.ktor.util.pipeline.*




fun Route.needAuth() {
    println("ehhh")
    intercept(ApplicationCallPipeline.Plugins) {
        val authService = AuthService(call)
        if (!authService.isAuthenticated()) {
            call.respondRedirect("/login")
            finish()  // Interrompe a execução da requisição
        }
    }
}