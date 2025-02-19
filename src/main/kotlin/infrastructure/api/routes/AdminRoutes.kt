package infrastructure.api.routes


import aplication.services.VisitLogService
import appModule.projetoService
import appModule.visitLogService
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
            val visitLogsResult = visitLogService.getAll()
            val projetosResult = projetoService.getAllProjetos()
            if (projetosResult.isFailure) {
                call.respondText("Failed to load projects", status = HttpStatusCode.InternalServerError)
                return@get
            }
            if (visitLogsResult.isFailure) {
                call.respondText("Failed to load visit logs", status = HttpStatusCode.InternalServerError)
                return@get
            }
            val visitLogs = visitLogsResult.getOrThrow()
            val projetos = projetosResult.getOrThrow()
            val visitLogsTotais = visitLogs
            // lista de visit logs sem repetição de ipAddress
            val visitLogsUnicos = visitLogs.distinctBy{ it.ipAddress }

            call.respond(FreeMarkerContent("/admin/admin.ftl",
                mapOf(
                    "projetos" to projetos,
                    "visitLogsTotais" to visitLogsTotais,
                    "visitLogsUnicos" to visitLogsUnicos
                )))


        }
    }
}


