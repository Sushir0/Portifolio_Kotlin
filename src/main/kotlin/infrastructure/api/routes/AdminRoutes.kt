package infrastructure.api.routes


import appModule.projetoService
import infrastructure.auth.needAuth
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlin.text.get

fun Route.adminRoutes() {
    route("/admin") {
        needAuth()
        get {

            val projetosResult = projetoService.getAllProjetos()
            if (projetosResult.isSuccess) {

                call.respond(FreeMarkerContent("/admin/admin.ftl", mapOf("projetos" to projetosResult.getOrThrow())))
            }else {
                call.respondText("Failed to load projects", status = HttpStatusCode.InternalServerError)
            }

        }
    }
}


