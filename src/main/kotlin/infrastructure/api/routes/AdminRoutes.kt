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
            val projetosResult = projetoService.getAllProjetos()
            val graficoTotalSemanalResult = visitLogService.getGraficoTotalSemanal()
            val graficoTotalMensalResult = visitLogService.getGraficoTotalMensal()
            val graficoTotalAnualResult = visitLogService.getGraficoTotalAnual()
            val graficoUniqueIpSemanalResult = visitLogService.getGraficoUniqueIpSemanal()
            val graficoUniqueIpMensalResult = visitLogService.getGraficoUniqueIpMensal()
            val graficoUniqueIpAnualResult = visitLogService.getGraficoUniqueIpAnual()


            if (projetosResult.isFailure) {
                call.respondText("Failed to load projects", status = HttpStatusCode.InternalServerError)
                return@get
            }
            if (graficoTotalSemanalResult.isFailure || graficoTotalMensalResult.isFailure || graficoTotalAnualResult.isFailure) {
                call.respondText("Failed to load visit logs", status = HttpStatusCode.InternalServerError)
                return@get
            }
            if (graficoUniqueIpSemanalResult.isFailure || graficoUniqueIpMensalResult.isFailure || graficoUniqueIpAnualResult.isFailure){
                call.respondText("Failed to load visit logs", status = HttpStatusCode.InternalServerError)
                return@get
            }

            val projetos = projetosResult.getOrThrow()
            val graficoTotalSemanal = graficoTotalSemanalResult.getOrThrow()
            val graficoTotalMensal = graficoTotalMensalResult.getOrThrow()
            val graficoTotalAnual = graficoTotalAnualResult.getOrThrow()
            val graficoUniqueIpSemanal = graficoUniqueIpSemanalResult.getOrThrow()
            val graficoUniqueIpMensal = graficoUniqueIpMensalResult.getOrThrow()
            val graficoUniqueIpAnual = graficoUniqueIpAnualResult.getOrThrow()


            call.respond(FreeMarkerContent("/admin/admin.ftl",
                mapOf(
                    "projetos" to projetos,
                    "graficoTotalSemanal" to graficoTotalSemanal,
                    "graficoTotalMensal" to graficoTotalMensal,
                    "graficoTotalAnual" to graficoTotalAnual,
                    "graficoUniqueIpSemanal" to graficoUniqueIpSemanal,
                    "graficoUniqueIpMensal" to graficoUniqueIpMensal,
                    "graficoUniqueIpAnual" to graficoUniqueIpAnual
                )))


        }
    }
}


