package infrastructure.api.routes

import aplication.services.AuthService
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import aplication.services.ProjetoService
import infrastructure.utils.Utils.getTagsFromProjetos
import io.ktor.http.*


fun Route.mainRoutes(
    projetoService : ProjetoService
) {
    get("/") {
        val projetos = projetoService.getAllProjetos()
        if(projetos.isSuccess){
            val authService = AuthService(call)
            val isAuthenticated = authService.isAuthenticated()

            val tags = getTagsFromProjetos(projetos.getOrThrow())

            call.respond(FreeMarkerContent("index.ftl", mapOf(
                "projetos" to projetos.getOrThrow(),
                "isAuthenticated" to isAuthenticated,
                "tags" to tags
                )))
        } else {
            call.respondText("Failed to load projects", status = HttpStatusCode.InternalServerError)
        }
    }
    static("/static") {
        resources("static")
    }
}

