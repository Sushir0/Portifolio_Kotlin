package infrastructure.api.routes

import aplication.services.AuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import java.util.concurrent.ConcurrentHashMap

fun Route.AuthRoutes() {
    route("/login") {
        get {
            call.respond(FreeMarkerContent("/admin/formulario_login.ftl", null))
        }

        val loginAttempts = ConcurrentHashMap<String, Pair<Int, Long>>() // (tentativas, último erro)
        val BLOCK_TIME_MS = 10 * 60 * 1000L // 10 minutos



        post {
            val ip = call.request.origin.remoteHost
            val params = call.receiveParameters()
            val username = params["username"] ?: ""
            val password = params["password"] ?: ""
            val authService = AuthService(call)

            val (attempts, lastErrorTime) = loginAttempts[ip] ?: (0 to 0L)
            val timeSinceLastAttempt = System.currentTimeMillis() - lastErrorTime

            // Verifica se o ip está bloqueado por 10 minutos
            if (attempts >= 20 && timeSinceLastAttempt < BLOCK_TIME_MS) {
                call.respond(HttpStatusCode.TooManyRequests, "Muitas tentativas. Tente novamente após 10 minutos.")
                return@post
            }

            // reseta o tempo de bloqueio
            if (timeSinceLastAttempt >= BLOCK_TIME_MS) {
                loginAttempts.remove(ip)
            }

            // delay progressivo conforme a quantidade de erros
            val delayTime = (attempts * 1000L).coerceAtMost(10_000)
            if (timeSinceLastAttempt < delayTime) {
                delay(delayTime - timeSinceLastAttempt)
            }

            if (authService.login(username, password)) {
                loginAttempts.remove(ip) // remove a penalidade caso o usuário seja autenticado
                call.respondRedirect("/admin")
            } else {
                loginAttempts[ip] = (attempts + 1) to System.currentTimeMillis() // registra o erro
                call.respond(FreeMarkerContent("/admin/formulario_login.ftl", mapOf("error" to "Usuário ou senha inválidos.")))
            }
        }
    }

    route("/logout"){
        get {
            val authService = AuthService(call)
            authService.logout()
            call.respondRedirect("/login")
        }
    }
}