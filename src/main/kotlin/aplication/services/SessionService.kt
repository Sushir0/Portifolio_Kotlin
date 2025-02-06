package aplication.services

import appModule.dotenv
import domain.models.UserSession
import io.ktor.server.application.*
import io.ktor.server.sessions.*

class AuthService(private val call: ApplicationCall) {

    private val ADMIN_USERNAME = dotenv["ADMIN_USERNAME"] ?: throw IllegalArgumentException("ADMIN_USERNAME environment variable not set")
    private val ADMIN_PASSWORD = dotenv["ADMIN_PASSWORD"] ?: throw IllegalArgumentException("ADMIN_PASSWORD environment variable not set")

    fun login(username: String, password: String): Boolean {

        return if (username == ADMIN_USERNAME && password == ADMIN_PASSWORD) {
            call.sessions.clear<UserSession>()
            call.sessions.set(UserSession(username))
            true
        } else {
            false
        }
    }

    fun isAuthenticated(): Boolean {
        return call.sessions.get<UserSession>() != null
    }

    fun logout() {
        call.sessions.clear<UserSession>()
    }
}