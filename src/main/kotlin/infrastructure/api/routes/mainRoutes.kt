package infrastructure.api.routes

import aplication.services.AuthService
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import aplication.services.ProjetoService
import appModule.visitLogService
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
            val visitantesUnicosResult = visitLogService.getVisitantesUnicos()
            val visitasTotaisResult = visitLogService.getVisitasTotais()

            if (visitantesUnicosResult.isFailure || visitasTotaisResult.isFailure) {
                call.respondText("Failed to load visit logs", status = HttpStatusCode.InternalServerError)
                return@get
            }

            val visitantesUnicos = visitantesUnicosResult.getOrThrow()
            val visitasTotais = visitasTotaisResult.getOrThrow()

            call.respond(FreeMarkerContent("index.ftl", mapOf(
                "projetos" to projetos.getOrThrow(),
                "isAuthenticated" to isAuthenticated,
                "tags" to tags,
                "visitantesUnicos" to visitantesUnicos,
                "visitasTotais" to visitasTotais

                )))
        } else {
            call.respondText("Failed to load projects", status = HttpStatusCode.InternalServerError)
        }
    }
    static("/static") {
        resources("static")
    }
}

