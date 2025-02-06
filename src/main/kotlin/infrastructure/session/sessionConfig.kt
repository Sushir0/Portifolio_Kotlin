package infrastructure.session

import domain.models.UserSession
import io.ktor.server.application.*
import io.ktor.server.sessions.*


fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserSession>("USER_SESSION", storage = SessionStorageMemory()) {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60 * 60 * 24
            cookie.httpOnly = true
            cookie.secure = true
            cookie.extensions["samesite"] = "strict"
        }
    }
}